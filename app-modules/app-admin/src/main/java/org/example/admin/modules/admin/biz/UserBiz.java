package org.example.admin.modules.admin.biz;

import org.example.common.biz.BaseBiz;
import org.example.admin.modules.admin.entity.User;
import org.example.admin.modules.admin.mapper.UserMapper;
import org.example.admin.modules.admin.util.Sha256PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ${DESCRIPTION}
 *
 * @author the sun
 * @create 2017-06-08 16:23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserBiz extends BaseBiz<UserMapper,User> {

    private Sha256PasswordEncoder encoder = new Sha256PasswordEncoder();

    @Override
    public void insertSelective(User entity) {
        String password = encoder.encode(entity.getPassword());
        entity.setPassword(password);
        super.insertSelective(entity);
    }

    @Override
    public void updateSelectiveById(User entity) {
        super.updateSelectiveById(entity);
    }

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    public User getUserByUsername(String username){
        User user = new User();
        user.setUsername(username);
        return mapper.selectOne(user);
    }


}
