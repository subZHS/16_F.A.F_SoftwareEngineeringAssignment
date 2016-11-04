package bl_Driver.userblservice_Driver;

import businesslogicservice.userblservice.LoginAndSignUpService;
import vo.UserVO;

public class LoginAndSignUpServiceImpl_Driver {
    public void drive(LoginAndSignUpService loginAndSignUpService){
        boolean result1 = loginAndSignUpService.login("原", "qwe123");
        if(result1)
           System.out.println("Login Succeed!\n");
       else
           System.out.println("Login Failed!\n");
        
        UserVO userVO = new UserVO("原","qwe123","12345675555");
 
        boolean result4 = loginAndSignUpService.add(userVO);
        if(result4)
           System.out.println("Sign up Succeed!\n");
       else
           System.out.println("Sign up Failed!\n");
        
    }
}