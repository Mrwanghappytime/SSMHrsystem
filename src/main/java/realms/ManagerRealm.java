package realms;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import po.Manager;
import service.ManagerService;
import util.MD5Encode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ManagerRealm extends AuthorizingRealm {
    @Autowired
    private ManagerService managerService;

    @Override
    public String getName() {
        return "manage";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        Manager manager = new Manager();
        manager.setName(username);
        List<Manager> manager1 = managerService.getManager(manager);
        if(manager1.size() != 1){
            return null;
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<>();
        //Set<String> permits = new HashSet<>();
        roles.add("manager");

        authorizationInfo.addRoles(roles);
        //authorizationInfo.addStringPermissions(permits);
        return authorizationInfo;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        Manager manager = new Manager();
        manager.setName(usernamePasswordToken.getUsername());
        List<Manager> managers = managerService.getManager(manager);
        if(managers.size() != 1){
            throw new UnknownAccountException("未知的用户" + manager.getName());
        }
        manager = managers.get(0);
        return new SimpleAuthenticationInfo(manager.getName(), MD5Encode.encode(manager.getName(),manager.getPassword()), ByteSource.Util.bytes(manager.getName()),getName());
    }
}
