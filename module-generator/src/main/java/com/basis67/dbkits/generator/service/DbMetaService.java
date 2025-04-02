package com.basis67.dbkits.generator.service;

import com.basis67.dbkits.generator.model.TableMeta;

import java.sql.SQLException;
import java.util.List;

public interface DbMetaService {
    /**
     * 读取SQL数据库元数据
     * @return 数据库表设计 {@link TableMeta}
     */
    List<TableMeta> readSqlDbMeta() throws SQLException;
}
