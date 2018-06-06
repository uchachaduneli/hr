package ge.economy.hr.test;

import com.sun.security.auth.module.NTSystem;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import java.util.Hashtable;

/**
 *
 *
 */
public class App {

    public static void main(String[] args) {
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://dc4.enterprise.gov.loc:389");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "enterprise\\developer");
        env.put(Context.SECURITY_CREDENTIALS, "1qaz!QAZ");

//        Hashtable<String, String> env = new Hashtable<String, String>();
//        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
//        env.put(Context.PROVIDER_URL, "ldap://dcns.nasp.gov.ge:389");
//        env.put(Context.SECURITY_AUTHENTICATION, "simple");
//        env.put(Context.SECURITY_PRINCIPAL, "nasp\\developer");
//        env.put(Context.SECURITY_CREDENTIALS, "1qaz!QAZ");
        try {

            DirContext ctx = new InitialLdapContext(env, null);
            SearchControls searchCtls = new SearchControls();
            searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String returnedAtts[] = {"dn", "sn", "ln", "mail", "ou", "cn", "uid", "displayName", "userPrincipalName", "telephonenumber", "title", "description", "objectCategory",
                    "distinguishedName", "department"};
            searchCtls.setReturningAttributes(returnedAtts);

            String searchFilter = "(&(objectClass=user))";

            String searchBase = "DC=enterprise,DC=gov,DC=loc";

            int totalResults = 0;

            NamingEnumeration answer = ctx.search(searchBase, searchFilter, searchCtls);

            int count = 0;
            LDapResponse response = new LDapResponse();
            while (answer.hasMoreElements()) {
                SearchResult sr = (SearchResult) answer.next();
                count++;
                System.out.println("\n ************************************************************* \n");

                System.out.println(sr.getNameInNamespace());
                response.setNameInNamespace(sr.getNameInNamespace());
                String[] arr = sr.getNameInNamespace().split(",");
                int counter = 0;
                for (String s : arr) {
                    if (s.split("=")[0] == "OU") {
                        if (counter == 0) {
                            response.setFirstOU(s.split("=")[1]);
                            counter++;
                        } else {
                            response.setSecondOU(s.split("=")[1]);
                            counter = 0;
                        }
                    }
                }
                Attributes attrs = sr.getAttributes();
                if (attrs != null) {
                    for (String a : returnedAtts) {
                        if (attrs.get(a) != null) {
                            System.out.println(a + ":" + attrs.get(a).get());
                        }
                    }
                }

            }
            System.out.println(count);
            ctx.close();

        } catch (NamingException e) {
            System.err.println("Problem searching directory: " + e.getMessage());
        }

        NTSystem ntSyst = new NTSystem();
        System.out.println(ntSyst.getName());
    }
}
