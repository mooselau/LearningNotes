package demo.aop;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    public UserServiceImpl() {
        System.out.println("===> UserServiceImpl initialized..");
    }

    @Override
    public void printUser(User user) {
        if (user == null) {
            throw new RuntimeException("User object is null..");
        }
        System.out.print("id: " + user.getId());
        System.out.print("\tusername: " + user.getUserName());
        System.out.println("\tnote: " + user.getNote());
    }
}

