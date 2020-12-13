package io.lazyegg.metadata.repository;

import com.alibaba.cola.exception.Assert;
import io.lazyegg.metadata.mapper.TablesDO;
import io.lazyegg.metadata.mapper.TablesMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TablesMapperTest {
    SqlSession sqlSession;
    TablesMapper tablesMapper;

    @Before
    public void before() {
        sqlSession = Mybatis3Utils.getCurrentSqlSession();
        tablesMapper = sqlSession.getMapper(TablesMapper.class);
    }

    @After
    public void after() {
        Mybatis3Utils.closeCurrentSession();
    }
    @Test
    public void testFindByID() {
        TablesDO user = tablesMapper.getByTableName("user");
        Assert.notNull(user);
        System.out.println("Write your test here");
    }
}
