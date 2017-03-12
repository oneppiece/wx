package com.dzy.wx.global;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidAbstractDataSource;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目配置类
 * Created by Administrator on 2016/12/25.
 * User:Administrator
 * Date:2016/12/25
 * Time:11:38
 */
@Configuration
public class WxConfig extends WebMvcConfigurerAdapter {
    /**
     * 直接进行跳转动作的Controller。即不做任何操作，直接返回视图的Controller
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        //registry.addViewController("/home").setViewName("home");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login").setViewName("login");
    }

    /**
     * js,图片,音乐等资源的处理
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/music/**").addResourceLocations("classpath:/music/");
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

    /**
     * 设置druid监控
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        StatViewServlet statViewServlet = new StatViewServlet();
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(statViewServlet, "/druid/*");
        servletRegistrationBean.setName("DruidStatView");
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("resetEnable", Boolean.TRUE.toString());
        initParameters.put("loginUsername", "druid");
        initParameters.put("loginPassword", "druid");
        initParameters.put("allow", "");
        initParameters.put("resetEnable", Boolean.FALSE.toString());
        // initParameters.put("deny", "1.1.1.1");
        servletRegistrationBean.setInitParameters(initParameters);
        return servletRegistrationBean;
    }

    /**
     * 使用druid数据库连接池
     */
    @Bean(initMethod = "init", destroyMethod = "close")
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        //配置这个属性的意义在于，如果存在多个数据源，监控的时候可以通过名字来区分开来。如果没有配置，将会生成一个名字，格式是："DataSource-" + System.identityHashCode(this). 另外配置此属性至少在1.0.5版本中是不起作用的，强行设置name会出错。
        druidDataSource.setName("dzy-mysql");
        //连接数据库的url
        druidDataSource.setUrl("jdbc:mysql://139.129.164.130:3306/dzy?serverTimezone=CTT&characterEncoding=utf-8");
        //连接数据库的用户名
        druidDataSource.setUsername("lol");
        //连接数据库的密码
        druidDataSource.setPassword("VGh6oEFhC6PnMZd+KUtreiCltWdHX3nTDvqS9t2reUe+7jSgaeIcghC+b9o63MgxqNvcMIEft6/ukkLfO+rSVg==");
        //初始化时建立物理连接的个数。初始化发生在显示调用init方法，或者第一次getConnection时
        druidDataSource.setInitialSize(2);
        //最大连接池数量
        druidDataSource.setMaxActive(10);
        //最小连接池数量
        druidDataSource.setMinIdle(1);
        //获取连接时最大等待时间，单位毫秒。配置了maxWait之后，缺省启用公平锁，并发效率会有所下降，如果需要可以通过配置useUnfairLock属性为true使用非公平锁。
        druidDataSource.setMaxWait(2000);
        //是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。
        druidDataSource.setPoolPreparedStatements(Boolean.FALSE);
        //要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些，比如说100
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(-1);
        //用来检测连接是否有效的sql，要求是一个查询语句，常用select 'x'。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会其作用。
        druidDataSource.setValidationQuery("select 'x'");
        //单位：秒，检测连接是否有效的超时时间。底层调用jdbc Statement对象的void setQueryTimeout(int seconds)方法
        druidDataSource.setValidationQueryTimeout(1);
        //申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
        druidDataSource.setTestOnBorrow(Boolean.FALSE);
        //归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
        druidDataSource.setTestOnReturn(Boolean.FALSE);
        //建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
        druidDataSource.setTestWhileIdle(Boolean.TRUE);
        //有两个含义：
        //1) Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接。
        //2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明
        druidDataSource.setTimeBetweenEvictionRunsMillis(DruidAbstractDataSource.DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS);
        //	连接保持空闲而不被驱逐的最长时间
        druidDataSource.setMinEvictableIdleTimeMillis(1000 * 40);
        //	物理连接初始化的时候执行的sql
        druidDataSource.setConnectionInitSqls(null);
        //当数据库抛出一些不可恢复的异常时，抛弃连接
        //druidDataSource.setExceptionSorter(null);
        //属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：
        //监控统计用的filter:stat
        //日志用的filter:log4j
        //防御sql注入的filter:wall
        try {
            druidDataSource.setFilters("config");
            druidDataSource.setConnectionProperties("config.decrypt=true");
            druidDataSource.setFilters("stat");
            druidDataSource.setFilters("log4j");
            druidDataSource.setFilters("wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //类型是List，如果同时配置了filters和proxyFilters，是组合关系，并非替换关系
        List<Filter> filters = new ArrayList<>();
        StatFilter statFilter = new StatFilter();
        statFilter.setLogSlowSql(Boolean.TRUE);
        statFilter.setSlowSqlMillis(1000);
        filters.add(new StatFilter());

        druidDataSource.setProxyFilters(filters);
        //这一项可配可不配，如果不配置druid会根据url自动识别dbType，然后选择相应的driverClassName
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setEnable(Boolean.TRUE);
        druidDataSource.setKeepAlive(Boolean.TRUE);
        return druidDataSource;
    }

    /**
     * redis
     *
     * @param redisConnectionFactory
     * @return
     */
//    @Bean
//    private RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }
}
