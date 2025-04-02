package com.basis67.dbkits.generator.service;

import com.basis67.dbkits.generator.model.TableMeta;
import com.basis67.dbkits.generator.util.TableMetaReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DbMetaServiceImpl implements DbMetaService {

    private final DataSource dataSource;

    @Override
    public List<TableMeta> readSqlDbMeta() throws SQLException {
        final TableMetaReader reader = new TableMetaReader(dataSource);

        return reader.readTables();
    }

}
