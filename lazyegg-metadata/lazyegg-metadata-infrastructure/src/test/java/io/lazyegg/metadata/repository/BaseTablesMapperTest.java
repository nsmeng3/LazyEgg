package io.lazyegg.metadata.repository;

import com.alibaba.cola.exception.Assert;
import io.lazyegg.metadata.mapper.TablesDO;
import io.lazyegg.metadata.mapper.BaseTablesMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BaseTablesMapperTest {
    SqlSession sqlSession;
    BaseTablesMapper baseTablesMapper;

    @Before
    public void before() {
        sqlSession = Mybatis3Utils.getCurrentSqlSession();
        baseTablesMapper = sqlSession.getMapper(BaseTablesMapper.class);
    }

    @After
    public void after() {
        Mybatis3Utils.closeCurrentSession();
    }
    @Test
    public void testFindByID() {
        TablesDO user = baseTablesMapper.getByTableName("user");
        Assert.notNull(user);
        System.out.println("Write your test here");
    }
}
