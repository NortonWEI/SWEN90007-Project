package com.freshmel.service;

import com.freshmel.dataMapper.VenderMapper;
import com.freshmel.model.Vender;

import java.sql.SQLException;

public class VenderService {
    public boolean register(Vender vender) throws SQLException {
        // 1 check is the email in database
        // 2 if not in database then insert to database
        // 2 else return false

        VenderMapper venderMapper = new VenderMapper();
        return venderMapper.insert(vender);
    }
    public Vender login(Vender vender) throws SQLException {
        // 1 check is the email and password in database correct
        // 2 if correct return true
        // 2 else return false
        VenderMapper venderMapper = new VenderMapper();
        return venderMapper.findByEmailANDPassword(vender);
    }
}
