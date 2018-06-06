package ge.economy.hr.services;

import ge.economy.hr.dto.OrganisationDTO;
import ge.economy.hr.dto.PersonalDTO;
import ge.economy.hr.dto.StructureDTO;
import ge.economy.hr.error.IncorrectAuthorizationExeption;
import ge.economy.hr.test.LDapResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * @author invisible
 */
@Service
public class LDAPService {

    @Autowired
    private StructureService structureService;
    @Autowired
    private PersonalService personalService;
    @Autowired
    private PositionService positionService;

    private final String ldapAdServerEconomy = "ldap://economy.ge:389";
    private final String ldapAdServerEDA = "ldap://enterprise.gov.loc:389";
    private int ECONOMY_TREE_PARENT_ID = 1;
    private int ENTERPRISE_TREE_PARENT_ID = 56;

    private Logger logger = Logger.getLogger(LDAPService.class);

    private List<String> matchPersonalMails = new ArrayList<>();

    public OrganisationDTO getOrganisation(String mail) {
        if (!mail.isEmpty()) {
            String mailIndex = mail.substring(mail.indexOf("@") + 1, mail.length());
            OrganisationDTO organisation = structureService.getOrganisationByMail(mailIndex);
            return organisation;
        }
        return null;
    }

    public void syncronizeTreeByOrganisation(String mail) {

        OrganisationDTO organisation = getOrganisation(mail);
        if (organisation != null) {
            try {
                String personalAttrs[] = {
                        "sn",
                        "mail",
                        "ou",
                        "cn",
                        "uid",
                        "displayName",
                        "userPrincipalName",
                        "telephonenumber",
                        "title",
                        "description",
                        "objectCategory",
                        "distinguishedName",
                        "department"};

                int parentStructureId = organisation.getParentTreeId();
                int selectedOrganisationId = organisation.getId();

                String userName = organisation.getUsername();
                String ldapPassword = organisation.getPassword();
                String ldapUsername = organisation.getDomain() + "\\" + userName;
                String searchBase = organisation.getDcName();
                String providerUrl = organisation.getLdapServerUrl();
                Hashtable<String, String> env = new Hashtable<>();

                env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
                env.put(Context.PROVIDER_URL, providerUrl);
                env.put(Context.SECURITY_AUTHENTICATION, "simple");
                env.put(Context.SECURITY_PRINCIPAL, ldapUsername);
                env.put(Context.SECURITY_CREDENTIALS, ldapPassword);

                DirContext ctx = new InitialLdapContext(env, null);

                SearchControls searchCtls = new SearchControls();
                searchCtls.setReturningAttributes(personalAttrs);
                searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

                String searchFilter = "(&(objectClass=user))";

                NamingEnumeration answer = ctx.search(searchBase, searchFilter, searchCtls);

                List<LDapResponse> responses = new ArrayList<>();

                while (answer.hasMoreElements()) {
                    LDapResponse response = new LDapResponse();
                    SearchResult searchResult = (SearchResult) answer.next();
                    response.setNameInNamespace(searchResult.getNameInNamespace());
                    String[] resultArray = searchResult.getNameInNamespace().split(",");
                    int counter = 0;

                    for (String resultItem : resultArray) {
                        if ("OU".equals(resultItem.split("=")[0])) {
                            if (counter == 0) {
                                response.setFirstOU(resultItem.split("=")[1]);
                                counter++;
                            } else {
                                response.setSecondOU(response.getFirstOU());
                                response.setFirstOU(resultItem.split("=")[1]);
                                counter = 0;
                            }
                        }
                    }
                    Attributes ldapUserAttrs = searchResult.getAttributes();
                    if (ldapUserAttrs != null
                            && ldapUserAttrs.get("userPrincipalName") != null
                            && ldapUserAttrs.get("title") != null) {
                        if (ldapUserAttrs.get("cn") != null) {
                            response.setCn(ldapUserAttrs.get("cn").get().toString());
                        }
                        if (ldapUserAttrs.get("userPrincipalName") != null) {
                            response.setMail(ldapUserAttrs.get("userPrincipalName").get().toString());
                        }
                        if (ldapUserAttrs.get("title") != null) {
                            response.setTitle(ldapUserAttrs.get("title").get().toString());
                        }
                        if (ldapUserAttrs.get("telephonenumber") != null) {
                            response.setTelephoneNumber(ldapUserAttrs.get("telephonenumber").get().toString());
                        }
                        responses.add(response);
                    }
                }
                List<StructureDTO> allStructures = structureService.getChildByStructureId(parentStructureId); // 1 means all of the structure
                for (LDapResponse lDapResponse : responses) {
                    //aq ewera != ratom agar maxsovs shevamowmo kargad
                    if (lDapResponse.getFirstOU() == null) {
                        continue;
                    }
                    if (lDapResponse.getFirstOU().equals("IT")) {
                        lDapResponse.setFirstOU("Administrative");
                    } else if (lDapResponse.getFirstOU().equals("Advisers")) {
                        lDapResponse.setFirstOU("Heads");
                        lDapResponse.setSecondOU("Advisers");
                    } else if (lDapResponse.getFirstOU().equals("Assistant")) {
                        lDapResponse.setFirstOU("Heads");
                        lDapResponse.setSecondOU("Assistant");
                    }
                    boolean matchWasDetected = false;
                    for (StructureDTO structure : allStructures) {
                        try {
                            if (lDapResponse.getSecondOU() == null) {
                                if (lDapResponse.getFirstOU().equals(structure.getLdapKey())) {
                                    //save Department Personal
                                    savePersonalInfofromLdap(lDapResponse, structure.getId(), selectedOrganisationId);
                                    break;
                                }
                            } else {
                                List<StructureDTO> childStructure = structureService.getChildByStructureId(structure.getId());
                                for (StructureDTO child : childStructure) {
                                    if (lDapResponse.getSecondOU().equals(child.getLdapKey())) {
                                        //save division Personal
                                        savePersonalInfofromLdap(lDapResponse, child.getId(), selectedOrganisationId);
                                        matchWasDetected = true;
                                        break;
                                    }
                                }
                            }
                            if (matchWasDetected) {
                                break;
                            }
                        } catch (Exception e) {
                            logger.error(e);
                        }
                    }
                }
                ctx.close();

                inactiveUsers(selectedOrganisationId);

            } catch (Exception e) {
                int a = 5;
                logger.error(e);
            }
        }
    }

    public void syncronizePersonalModify(String mail, String ldapPassword) {
        try {

            String personalAttrs[] = {
                    "sn",
                    "mail",
                    "ou",
                    "cn",
                    "uid",
                    "displayName",
                    "userPrincipalName",
                    "telephonenumber",
                    "title",
                    "description",
                    "objectCategory",
                    "distinguishedName",
                    "department"};

            boolean isEDA = false;
            int parentStructureId = ECONOMY_TREE_PARENT_ID;
            int selectedOrganisationId = StructureDTO.ECONOMY_TREE;
            if (mail.indexOf("enterprise") > 0) {
                isEDA = true;
            }
            String userName = mail.substring(0, mail.indexOf("@"));
            String ldapUsername = "economy\\" + userName;
            String searchBase = "DC=economy,DC=ge";
            Hashtable<String, String> env = new Hashtable<>();

            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            if (isEDA) {
                parentStructureId = ENTERPRISE_TREE_PARENT_ID;
                selectedOrganisationId = StructureDTO.ENTERPRISE_TREE;
                env.put(Context.PROVIDER_URL, ldapAdServerEDA);
                searchBase = "DC=enterprise,DC=gov,DC=loc";
                ldapUsername = "enterprise\\" + userName;
            } else {
                env.put(Context.PROVIDER_URL, ldapAdServerEconomy);
            }
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PRINCIPAL, ldapUsername);
            env.put(Context.SECURITY_CREDENTIALS, ldapPassword);

            DirContext ctx = new InitialLdapContext(env, null);

            SearchControls searchCtls = new SearchControls();
            searchCtls.setReturningAttributes(personalAttrs);
            searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

            String searchFilter = "(&(objectClass=user))";

            NamingEnumeration answer = ctx.search(searchBase, searchFilter, searchCtls);

            List<LDapResponse> responses = new ArrayList<>();

            while (answer.hasMoreElements()) {
                LDapResponse response = new LDapResponse();
                SearchResult searchResult = (SearchResult) answer.next();
                response.setNameInNamespace(searchResult.getNameInNamespace());
                String[] resultArray = searchResult.getNameInNamespace().split(",");
                int counter = 0;

                for (String resultItem : resultArray) {
                    if ("OU".equals(resultItem.split("=")[0])) {
                        if (counter == 0) {
                            response.setFirstOU(resultItem.split("=")[1]);
                            counter++;
                        } else {
                            response.setSecondOU(response.getFirstOU());
                            response.setFirstOU(resultItem.split("=")[1]);
                            counter = 0;
                        }
                    }
                }
                Attributes ldapUserAttrs = searchResult.getAttributes();
                if (ldapUserAttrs != null
                        && ldapUserAttrs.get("userPrincipalName") != null
                        && ldapUserAttrs.get("title") != null) {
                    if (ldapUserAttrs.get("cn") != null) {
                        response.setCn(ldapUserAttrs.get("cn").get().toString());
                    }
                    if (ldapUserAttrs.get("userPrincipalName") != null) {
                        response.setMail(ldapUserAttrs.get("userPrincipalName").get().toString());
                    }
                    if (ldapUserAttrs.get("title") != null) {
                        response.setTitle(ldapUserAttrs.get("title").get().toString());
                    }
                    if (ldapUserAttrs.get("telephonenumber") != null) {
                        response.setTelephoneNumber(ldapUserAttrs.get("telephonenumber").get().toString());
                    }
                    responses.add(response);
                }
            }
            List<StructureDTO> allStructures = structureService.getChildByStructureId(parentStructureId); // 1 means all of the structure
            for (LDapResponse lDapResponse : responses) {
                //aq ewera != ratom agar maxsovs shevamowmo kargad
                if (lDapResponse.getFirstOU() == null) {
                    continue;
                }
                if (lDapResponse.getFirstOU().equals("IT")) {
                    lDapResponse.setFirstOU("Administrative");
                } else if (lDapResponse.getFirstOU().equals("Advisers")) {
                    lDapResponse.setFirstOU("Heads");
                    lDapResponse.setSecondOU("Advisers");
                } else if (lDapResponse.getFirstOU().equals("Assistant")) {
                    lDapResponse.setFirstOU("Heads");
                    lDapResponse.setSecondOU("Assistant");
                }
                boolean matchWasDetected = false;
                for (StructureDTO structure : allStructures) {
                    try {
                        if (lDapResponse.getSecondOU() == null) {
                            if (lDapResponse.getFirstOU().equals(structure.getLdapKey())) {
                                //save Department Personal
                                savePersonalInfofromLdap(lDapResponse, structure.getId(), selectedOrganisationId);
                                break;
                            }
                        } else {
                            List<StructureDTO> childStructure = structureService.getChildByStructureId(structure.getId());
                            for (StructureDTO child : childStructure) {
                                if (lDapResponse.getSecondOU().equals(child.getLdapKey())) {
                                    //save division Personal
                                    savePersonalInfofromLdap(lDapResponse, child.getId(), selectedOrganisationId);
                                    matchWasDetected = true;
                                    break;
                                }
                            }
                        }
                        if (matchWasDetected) {
                            break;
                        }
                    } catch (Exception e) {
                        logger.error(e);
                    }
                }
            }
            ctx.close();

            inactiveUsers(selectedOrganisationId);

        } catch (Exception e) {
            int a = 5;
            logger.error(e);
        }
    }

    public void syncronizePersonal(String mail, String ldapPassword) {
        try {
            String personalAttrs[] = {
                    "sn",
                    "mail",
                    "ou",
                    "cn",
                    "uid",
                    "displayName",
                    "userPrincipalName",
                    "telephonenumber",
                    "title",
                    "description",
                    "objectCategory",
                    "distinguishedName",
                    "department"};

            String ldapUsername = "economy\\" + mail.substring(0, mail.indexOf("@"));

            Hashtable<String, String> env = new Hashtable<>();

            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, ldapAdServerEconomy);
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PRINCIPAL, ldapUsername);
            env.put(Context.SECURITY_CREDENTIALS, ldapPassword);

            DirContext ctx = new InitialLdapContext(env, null);

            SearchControls searchCtls = new SearchControls();
            searchCtls.setReturningAttributes(personalAttrs);
            searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

            String searchFilter = "(&(objectClass=user))";
            String searchBase = "DC=economy,DC=ge";

            NamingEnumeration answer = ctx.search(searchBase, searchFilter, searchCtls);

            List<LDapResponse> responses = new ArrayList<>();

            while (answer.hasMoreElements()) {
                LDapResponse response = new LDapResponse();
                SearchResult searchResult = (SearchResult) answer.next();
                response.setNameInNamespace(searchResult.getNameInNamespace());
                String[] resultArray = searchResult.getNameInNamespace().split(",");
                int counter = 0;

                for (String resultItem : resultArray) {
                    if ("OU".equals(resultItem.split("=")[0])) {
                        if (counter == 0) {
                            response.setFirstOU(resultItem.split("=")[1]);
                            counter++;
                        } else {
                            response.setSecondOU(response.getFirstOU());
                            response.setFirstOU(resultItem.split("=")[1]);
                            counter = 0;
                        }
                    }
                }
                Attributes ldapUserAttrs = searchResult.getAttributes();
                if (ldapUserAttrs != null
                        && ldapUserAttrs.get("userPrincipalName") != null
                        && ldapUserAttrs.get("title") != null) {
                    if (ldapUserAttrs.get("cn") != null) {
                        response.setCn(ldapUserAttrs.get("cn").get().toString());
                    }
                    if (ldapUserAttrs.get("userPrincipalName") != null) {
                        response.setMail(ldapUserAttrs.get("userPrincipalName").get().toString());
                    }
                    if (ldapUserAttrs.get("title") != null) {
                        response.setTitle(ldapUserAttrs.get("title").get().toString());
                    }
                    if (ldapUserAttrs.get("telephonenumber") != null) {
                        response.setTelephoneNumber(ldapUserAttrs.get("telephonenumber").get().toString());
                    }
                    responses.add(response);
                }
            }
            List<StructureDTO> allStructures = structureService.getChildByStructureId(1); // 1 means all of the structure
            for (LDapResponse lDapResponse : responses) {
                //aq ewera != ratom agar maxsovs shevamowmo kargad
                if (lDapResponse.getFirstOU() == null) {
                    continue;
                }
                if (lDapResponse.getFirstOU().equals("HR")) {
                    int a = 5;
                }
                boolean matchWasDetected = false;
                for (StructureDTO structure : allStructures) {
                    try {
                        if (lDapResponse.getSecondOU() == null) {
                            if (lDapResponse.getFirstOU().equals(structure.getLdapKey())) {
                                //save Department Personal
                                savePersonalInfofromLdap(lDapResponse, structure.getId(), StructureDTO.ECONOMY_TREE);
                                break;
                            }
                        } else {
                            List<StructureDTO> childStructure = structureService.getChildByStructureId(structure.getId());
                            for (StructureDTO child : childStructure) {
                                if (lDapResponse.getSecondOU().equals(child.getLdapKey())) {
                                    //save division Personal
                                    savePersonalInfofromLdap(lDapResponse, child.getId(), StructureDTO.ECONOMY_TREE);
                                    matchWasDetected = true;
                                    break;
                                }
                            }
                        }
                        if (matchWasDetected) {
                            break;
                        }
                    } catch (Exception e) {
                        logger.error(e);
                    }
                }
            }
            ctx.close();
            inactiveUsers(StructureDTO.ECONOMY_TREE);

        } catch (Exception e) {
            int a = 5;
            logger.error(e);
        }
    }

    public void savePersonalInfofromLdap(LDapResponse response, int structureId, int organisationId) throws Exception {
        try {
            ge.economy.hr.request.Personal _personal = new ge.economy.hr.request.Personal();
            if (response.getCn() != null && response.getCn().length() > 1) {
                _personal.setFirstName(response.getCn().substring(0, response.getCn().indexOf(" ")));
                _personal.setLastName(response.getCn().substring(response.getCn().indexOf(" ")));
            }
            String mail = response.getMail().replace(".loc", ".ge");
            matchPersonalMails.add(mail);
            _personal.setMail(mail);

            // this is personal position
            if (response.getTitle() != null) {
                if (positionService.getPositionByLdapKey(response.getTitle()) != null) {
                    _personal.setPositionId(positionService.getPositionByLdapKey(response.getTitle()).getId());
                } else {
                    _personal.setPositionId(7);
                }
            } else {
                _personal.setPositionId(7);
            }
            _personal.setStructureId(structureId);
            _personal.setOrganisationId(organisationId);
            personalService.savePersonal(_personal);
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    public PersonalDTO ldapAuth(String mail, String ldapPassword) throws IncorrectAuthorizationExeption {
        try {
            String userName = mail.substring(0, mail.indexOf("@"));
            String ldapServer = ldapAdServerEconomy;
            String ldapUsername = "economy\\" + userName;
            if (mail.indexOf("enterprise") > 0) {
                ldapUsername = "enterprise\\" + userName;
                ldapServer = ldapAdServerEDA;
            }
            Hashtable<String, String> env = new Hashtable<>();

            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, ldapServer);
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PRINCIPAL, ldapUsername);
            env.put(Context.SECURITY_CREDENTIALS, ldapPassword);

            DirContext ctx = new InitialLdapContext(env, null);
            return personalService.getPersonalByMail(mail);
        } catch (Exception ex) {
            logger.error(ex);
            throw new IncorrectAuthorizationExeption("username or password incorrect");
        }
    }

    public void inactiveUsers(int organisationId) {
        if (matchPersonalMails != null && matchPersonalMails.size() > 0) {
            personalService.inactivePersonal(matchPersonalMails, organisationId);
        }
    }

    public List<String> getMatchPersonalMails() {
        return matchPersonalMails;
    }

    public void setMatchPersonalMails(List<String> matchPersonalMails) {
        this.matchPersonalMails = matchPersonalMails;
    }

}

enum LDAPPositionKey {

}
