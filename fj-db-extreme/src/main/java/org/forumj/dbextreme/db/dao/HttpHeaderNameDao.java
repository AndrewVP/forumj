package org.forumj.dbextreme.db.dao;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.db.entity.Entity;
import org.forumj.common.db.entity.HttpHeaderName;
import org.forumj.dbextreme.db.entity.HttpHeaderNameImpl;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.forumj.dbextreme.db.dao.tool.QueryBuilder.getCheckHeaderNameExistsQuery;
import static org.forumj.dbextreme.db.dao.tool.QueryBuilder.getCreateHttpHeaderNameQuery;

/**
 * Created by Andrew on 13/05/2017.
 */
public class HttpHeaderNameDao extends BaseDao {

    public HttpHeaderName getObject(){
        return new HttpHeaderNameImpl();
    }

    @Override
    protected int prepareStatementForUpdate(Entity entity, PreparedStatement st) throws SQLException {
        int parameterIndex = 0;
        if (entity instanceof HttpHeaderName){
            HttpHeaderName httpHeaderName = (HttpHeaderName) entity;
            st.setString(++parameterIndex, httpHeaderName.getName());
        }
        return parameterIndex;
    }

    public Long find(String name) throws IOException, SQLException, ConfigurationException {
        return (Long) readValue(getCheckHeaderNameExistsQuery(), name);
    }

    @Override
    protected String getCreateQuery() throws IOException {
        return getCreateHttpHeaderNameQuery();
    }
}