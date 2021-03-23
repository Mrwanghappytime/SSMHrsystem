package realms;


import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import po.Employee;
import service.EmployeeService;
import util.MD5Encode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmployeeRealm extends AuthorizingRealm {
    @Autowired
    private EmployeeService employeeService;
    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return true;
    }
    @Override
    public String getName() {
        return "employee";
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        Employee employee = new Employee();
        employee.setName(usernamePasswordToken.getUsername());
        List<Employee> employees = employeeService.getEmployee(employee);
        if(employees.size() != 1){
            throw new UnknownAccountException("未知的用户2" + employee.getName());
        }
        employee = employees.get(0);
        return new SimpleAuthenticationInfo(employee.getName(), MD5Encode.encode(employee.getName(),employee.getPassword()), ByteSource.Util.bytes(employee.getName()),getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        Employee employee = new Employee();
        employee.setName(username);
        List<Employee> employees = employeeService.getEmployee(employee);
        if(employees.size() != 1){
            return null;
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<>();
        //Set<String> permits = new HashSet<>();
        roles.add("employee");
        authorizationInfo.addRoles(roles);
        //authorizationInfo.addStringPermissions(permits);
        return authorizationInfo;
    }
}
