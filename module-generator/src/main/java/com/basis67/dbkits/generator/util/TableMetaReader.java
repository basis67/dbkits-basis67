package com.basis67.dbkits.generator.util;

import com.basis67.dbkits.generator.model.ColumnMeta;
import com.basis67.dbkits.generator.model.TableMeta;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 数据库表设计（表元数据）读取器 <br/>
 * 读取数据库中已存在的表设计，并生成  {@link TableMeta}
 */
public class TableMetaReader {
    private final Connection connection;

    public TableMetaReader(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
    }

    public List<TableMeta> readTables() throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});

        List<TableMeta> tableMetas = new ArrayList<>();
        while (tables.next()) {
            TableMeta tableMeta = new TableMeta();
            String tableName = tables.getString("TABLE_NAME");
            tableMeta.setTableName(tableName);
            tableMeta.setClassName(StringUtils.toCamelCase(tableName, true));
            tableMeta.setComment(tables.getString("REMARKS"));

            // 读取主键信息
            ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, tableName);
            Set<String> pkColumns = new HashSet<>();
            while (primaryKeys.next()) {
                pkColumns.add(primaryKeys.getString("COLUMN_NAME"));
            }
            // 读取列信息
            ResultSet columns = metaData.getColumns(null, null, tableName, null);
            List<ColumnMeta> columnMetas = new ArrayList<>();
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                int jdbcType = columns.getInt("DATA_TYPE");
                String remarks = columns.getString("REMARKS");
                int columnSize = columns.getInt("COLUMN_SIZE");
                int nullable = columns.getInt("NULLABLE");


                ColumnMeta column = new ColumnMeta();
                column.setColumnName(columnName);
                column.setJavaField(StringUtils.toCamelCase(columnName, false));
                column.setJavaType(JdbcTypeUtil.parse(jdbcType));
                column.setComment(remarks);
                column.setNullable(DatabaseMetaData.columnNullable == nullable);
                column.setJdbcType(jdbcType);
                column.setLength(columnSize);

                // 处理列信息时设置主键标识
                if (pkColumns.contains(columnName)) {
                    column.setPrimaryKey(true);
                }

                columnMetas.add(column);
            }

            tableMeta.setColumns(columnMetas);

            // 设置表的主键列（取第一个）
            if (!pkColumns.isEmpty()) {
                tableMeta.setPrimaryKey(
                        columnMetas.stream()
                                .filter(ColumnMeta::isPrimaryKey)
                                .findFirst()
                                .orElse(null)
                );
            }

            tableMetas.add(tableMeta);
        }

        return tableMetas;
    }

}