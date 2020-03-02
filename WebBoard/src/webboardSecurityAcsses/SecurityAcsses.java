package webboardSecurityAcsses;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;



public class SecurityAcsses {
 
    // ��������� ������� �� ������ 'request' ����� � ������� ��� ���.
    public static boolean isSecurityPage(HttpServletRequest request) {
        String urlPattern = UrlPatternUtils.getUrlPattern(request);
        Set<String> roles = urlAcsses.getAllAppRoles();
        System.out.println("SecurityAcsses roles  " +  roles);
        for (String role : roles) {
            List<String> urlPatterns = urlAcsses.getUrlPatternsForRole(role);
            System.out.println("urlPatterns  " +  urlPatterns);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
            	System.out.println(urlPattern + " for " + role + " Security Acsses OK");
                return true;
            }
        }
        System.out.println(urlPattern + " Security Acsses false");
        return false;
    }
 
    // ��������� ����� �� ������ 'request' ���������� ����?
    public static boolean hasPermission(HttpServletRequest request) {
        String urlPattern = UrlPatternUtils.getUrlPattern(request);
        Set<String> allRoles = urlAcsses.getAllAppRoles();
        System.out.println("allRoles - " + allRoles);
        for (String role : allRoles) {
            if (!request.isUserInRole(role)) {
                continue;
            }
            List<String> urlPatterns = urlAcsses.getUrlPatternsForRole(role);
            System.out.println("urlPatterns - " + urlPatterns);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
            	System.out.println(urlPattern + " - for " + role + " access ok");
                return true;
            }
        }
        return false;
    }
}