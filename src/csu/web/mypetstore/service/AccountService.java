package csu.web.mypetstore.service;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.persistence.AccountDao;
import csu.web.mypetstore.persistence.Impl.AccountDaoImpl;

public class AccountService {
    private AccountDao accountDao;

    public AccountService() {
        this.accountDao = new AccountDaoImpl();
    }

    // 验证用户登录
    public Account getAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        return accountDao.getAccountByUsernameAndPassword(account);
    }

    // 创建新用户
    public void createAccount(Account account) {
        // 假设有方法确保密码加密
        account.setPassword(encryptPassword(account.getPassword()));
        accountDao.insertAccount(account);
        accountDao.insertSignon(account);
        accountDao.insertProfile(account);
    }

    // 检查用户名是否已存在
    public boolean usernameExists(String username) {
        return accountDao.getAccountByUsername(username) != null;
    }

    // 示例密码加密方法（需根据实际需求实现）
    private String encryptPassword(String password) {
        // 实现密码加密逻辑，例如使用哈希函数
        return password; // 简化处理，实际应使用加密算法
    }
}
