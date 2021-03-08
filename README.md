mbg-plugins
===
MyBatis Generator 工具的拓展插件包
## 插件列表
| 插件名称  | 插件类名 | 插件描述  | 详情 |
| ---- | ---- | ---- | ---- |
| 注释插件  | MyCommentGenerator | 一个高度自由的注释插件  | [注释插件](#1.注释插件) |
| Lombok插件  | LombokPlugin | 提供Lombok支持  | 维护中 |
| 序列化插件  | MySerializablePlugin | 提供序列化支持  | 维护中 |
| 文件覆盖插件  | MyOverIsMergeablePlugin | 覆盖xml文件  |  |
| 注解缓存插件  | AnnotationCachePlugin | 提供注解式的缓存支持  | 维护中 |
| dsl模式注释插件  | DSLCommentGeneratorPlugin | 一个高度自由的注释插件  | [dsl模式注释插件](#1.2dsl模式注释插件) |
| dsl模式内部类访问控制符插件  | DSLInnerClassVisibilityPlugin | 提供管理dsl模式内部类访问控制符功能  | [dsl模式内部类访问控制符插件](#1.3dsl模式内部类访问控制符插件) |
| dsl模式P3C规范插件  | P3CDynamicSqlSupportPlugin | 使mbg工具dsl模式生成的代码符合阿里P3C规范  | [dsl模式P3C规范插件](#1.4dsl模式P3C规范插件) |
## 1.注释插件
Mybatis Generator 原生插件需要实现CommentGenerator接口实现自定义注释，而本插件则使用模版实现。可通过实现TemplateEngine与TemplateContainer接口适配模版引擎，本插件提供了Velocity与FreeMaker的默认实现。
### 1.1参数说明
| 参数名称 | 参数说明 | 默认值  | 是否必须 |
| ---- | ---- | ---- | ---- |
| suppressAllComments | 属性为true则不生成自定义注释 | false | 否 |
| suppressJavaFileComments | 属性为true则不生成自定义文件注释 | true | 否 |
| dateFormat | 日期格式 | yyyy-MM-dd hh:mm:ss | 否 |
| author | 作者 |  | 否 |
| company | 公司 |  | 否 |
| version | 版本 |  | 否 |
| copyright | 版权信息 |  | 否 |
| commentType | 注释模版实现类 | com.htyggh.utils.mybatis.generator.templates.comment.internal.velocity.DefaultCommentTemplate | 否 |
| 自定义参数 | 自定义key与value可直接在模版文件内使用（注：key值不要与上述参数名重复） |  | 否 |
### 1.2样例
```xml
<!-- 生成数据库字段注释及自定义注释 【扩展插件】 -->
<commentGenerator type="com.htyggh.utils.mybatis.generator.comment.MyCommentGenerator">
    <property name="suppressAllComments" value="false"/>
    <property name="suppressJavaFileComments" value="true"/>
    <property name="dateFormat" value="yyyy-MM-dd hh:mm:ss"/>
    <property name="author" value="htyggh"/>
    <property name="company" value="" />
    <property name="version" value="1.0"/>
    <property name="copyright" value="" />
    <!-- 自定义参数 -->
    <property name="custom" value="custom" />
    <property name="commentType" value="com.htyggh.utils.mybatis.generator.templates.comment.internal.velocity.SimpleCommentTemplate"/>
</commentGenerator>
```
```java
/**
 * sys_authority表实体类
 *
 * @author htyggh
 * @date 2021-03-08 12:47:52
 * @version 1.0
 */
public class AuthorityDO {
    /**
     * 字段描述：主键ID  [主键] [默认值：无默认值]
     */
    private Long id;

    /**
     * 字段描述：权限名称  [主键] [默认值：]
     */
    private String authorityName;

    /**
     * 字段描述：权限码  [不可空] [默认值：]
     */
    private String authorityCode;

    /**
     * 字段描述：权限描述  [不可空] [默认值：]
     */
    private String authorityDesc;

    /**
     * 字段描述：逻辑删除标记 1 表示已删除，0 表示未删除。  [不可空] [默认值：0]
     */
    private Byte isDeleted;

    /**
     * 字段描述：乐观锁版本  [不可空] [默认值：0]
     */
    private Integer lockVersion;

    /**
     * 字段描述：创建时间  [不可空] [默认值：CURRENT_TIMESTAMP]
     */
    private LocalDateTime createTime;

    /**
     * 字段描述：创建人ID  [不可空] [默认值：无默认值]
     */
    private Long createId;

    /**
     * 字段描述：最后修改时间  [不可空] [默认值：CURRENT_TIMESTAMP]
     */
    private LocalDateTime updateTime;

    /**
     * 字段描述：最后修改人ID  [不可空] [默认值：无默认值]
     */
    private Long updateId;

    /**
     * 描述：获取主键ID
     *
     * @return 主键ID [主键] [默认值：无默认值]
     */
    public Long getId() {
        return id;
    }

    /**
     * 描述：赋予主键ID
     *
     * @param id 主键ID [主键] [默认值：无默认值]
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 描述：获取权限名称
     *
     * @return 权限名称 [主键] [默认值：]
     */
    public String getAuthorityName() {
        return authorityName;
    }

    /**
     * 描述：赋予权限名称
     *
     * @param authorityName 权限名称 [主键] [默认值：]
     */
    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName == null ? null : authorityName.trim();
    }

    /**
     * 描述：获取权限码
     *
     * @return 权限码 [不可空] [默认值：]
     */
    public String getAuthorityCode() {
        return authorityCode;
    }

    /**
     * 描述：赋予权限码
     *
     * @param authorityCode 权限码 [不可空] [默认值：]
     */
    public void setAuthorityCode(String authorityCode) {
        this.authorityCode = authorityCode == null ? null : authorityCode.trim();
    }

    /**
     * 描述：获取权限描述
     *
     * @return 权限描述 [不可空] [默认值：]
     */
    public String getAuthorityDesc() {
        return authorityDesc;
    }

    /**
     * 描述：赋予权限描述
     *
     * @param authorityDesc 权限描述 [不可空] [默认值：]
     */
    public void setAuthorityDesc(String authorityDesc) {
        this.authorityDesc = authorityDesc == null ? null : authorityDesc.trim();
    }

    /**
     * 描述：获取逻辑删除标记 1 表示已删除，0 表示未删除。
     *
     * @return 逻辑删除标记 1 表示已删除，0 表示未删除。 [不可空] [默认值：0]
     */
    public Byte getIsDeleted() {
        return isDeleted;
    }

    /**
     * 描述：赋予逻辑删除标记 1 表示已删除，0 表示未删除。
     *
     * @param isDeleted 逻辑删除标记 1 表示已删除，0 表示未删除。 [不可空] [默认值：0]
     */
    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 描述：获取乐观锁版本
     *
     * @return 乐观锁版本 [不可空] [默认值：0]
     */
    public Integer getLockVersion() {
        return lockVersion;
    }

    /**
     * 描述：赋予乐观锁版本
     *
     * @param lockVersion 乐观锁版本 [不可空] [默认值：0]
     */
    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    /**
     * 描述：获取创建时间
     *
     * @return 创建时间 [不可空] [默认值：CURRENT_TIMESTAMP]
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * 描述：赋予创建时间
     *
     * @param createTime 创建时间 [不可空] [默认值：CURRENT_TIMESTAMP]
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * 描述：获取创建人ID
     *
     * @return 创建人ID [不可空] [默认值：无默认值]
     */
    public Long getCreateId() {
        return createId;
    }

    /**
     * 描述：赋予创建人ID
     *
     * @param createId 创建人ID [不可空] [默认值：无默认值]
     */
    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    /**
     * 描述：获取最后修改时间
     *
     * @return 最后修改时间 [不可空] [默认值：CURRENT_TIMESTAMP]
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * 描述：赋予最后修改时间
     *
     * @param updateTime 最后修改时间 [不可空] [默认值：CURRENT_TIMESTAMP]
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 描述：获取最后修改人ID
     *
     * @return 最后修改人ID [不可空] [默认值：无默认值]
     */
    public Long getUpdateId() {
        return updateId;
    }

    /**
     * 描述：赋予最后修改人ID
     *
     * @param updateId 最后修改人ID [不可空] [默认值：无默认值]
     */
    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }
}
```
## 1.2dsl模式注释插件
参数与样例均与[注释插件](#1.注释插件)相同，这里编写两个注释插件的原因是mbg工具会清除dsl模式下由CommentGenerator子类生成的注释，详情请查看我向mbg工具的作者反馈的[问题。](https://github.com/mybatis/generator/issues/463)
## 1.2.1 样例
注：使用此插件请先
```xml
<!-- 生成数据库字段注释及自定义注释 【扩展插件】 -->
<plugin type="com.htyggh.utils.mybatis.generator.plugins.dsl.DSLCommentGeneratorPlugin">
    <property name="suppressAllComments" value="false"/>
    <property name="suppressJavaFileComments" value="true"/>
    <property name="dateFormat" value="yyyy-MM-dd hh:mm:ss"/>
    <property name="author" value="htyggh"/>
    <property name="company" value="" />
    <property name="version" value="1.0"/>
    <property name="copyright" value="" />
    <!-- 自定义参数 -->
    <property name="custom" value="custom" />
    <property name="commentType" value="com.htyggh.utils.mybatis.generator.templates.comment.internal.velocity.SimpleCommentTemplate"/>
</plugin>
```
## 1.3dsl模式内部类访问控制符插件
提供了mbg工具dsl模式下对于DynamicSqlSupport类内部类访问控制符的修改功能，默认情况下mbg工具设置的DynamicSqlSupport类内部类访问控制符事public。
### 1.3.1参数说明
| 参数名称 | 参数说明 | 默认值  | 是否必须 |
| ---- | ---- | ---- | ---- |
| innerClassVisibility | 内部类访问控制符 | private | 否 |
| innerClassFieldVisibility | 内部类字段访问控制符 | private | 否 |
| innerClassMethodVisibility | 内部类方法访问控制符 | private | 否 |
### 1.3.2样例
```xml
<!-- 设置DSL支持类的内部类访问控制符 -->
<plugin type="com.htyggh.utils.mybatis.generator.plugins.dsl.DSLInnerClassVisibilityPlugin">
    <property name="innerClassVisibility" value="private"/>
    <property name="innerClassFieldVisibility" value="private"/>
    <property name="innerClassMethodVisibility" value="private"/>
</plugin>
```
```java
/**
 * sys_authority表，表对象
 *
 * @author htyggh
 * @date 2021-03-08 01:38:19
 * @version 1.0
 */
private static final class AuthorityDO extends SqlTable {
    /**
     * <p>字段描述：sys_authority表id列，列对象</p>
     */
    private final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

    /**
     * <p>字段描述：sys_authority表authority_name列，列对象</p>
     */
    private final SqlColumn<String> authorityName = column("authority_name", JDBCType.VARCHAR);

    /**
     * <p>字段描述：sys_authority表authority_code列，列对象</p>
     */
    private final SqlColumn<String> authorityCode = column("authority_code", JDBCType.VARCHAR);

    /**
     * <p>字段描述：sys_authority表authority_desc列，列对象</p>
     */
    private final SqlColumn<String> authorityDesc = column("authority_desc", JDBCType.VARCHAR);

    /**
     * <p>字段描述：sys_authority表is_deleted列，列对象</p>
     */
    private final SqlColumn<Byte> isDeleted = column("is_deleted", JDBCType.TINYINT);

    /**
     * <p>字段描述：sys_authority表lock_version列，列对象</p>
     */
    private final SqlColumn<Integer> lockVersion = column("lock_version", JDBCType.INTEGER);

    /**
     * <p>字段描述：sys_authority表create_time列，列对象</p>
     */
    private final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

    /**
     * <p>字段描述：sys_authority表create_id列，列对象</p>
     */
    private final SqlColumn<Long> createId = column("create_id", JDBCType.BIGINT);

    /**
     * <p>字段描述：sys_authority表update_time列，列对象</p>
     */
    private final SqlColumn<LocalDateTime> updateTime = column("update_time", JDBCType.TIMESTAMP);

    /**
     * <p>字段描述：sys_authority表update_id列，列对象</p>
     */
    private final SqlColumn<Long> updateId = column("update_id", JDBCType.BIGINT);

    private AuthorityDO() {
        super("sys_authority");
    }
}
```
## 1.4dsl模式P3C规范插件
是mbg工具dsl模式下生成的代码符合阿里巴巴p3c代码规范
### 1.1样例
```xml
<plugin type="com.htyggh.utils.mybatis.generator.plugins.dsl.P3CDynamicSqlSupportPlugin"/>
```
```java
/**
 * sys_authority表mapper接口
 *
 * @author htyggh
 * @date 2021-03-08 01:44:26
 * @version 1.0
 */
@Mapper
public interface AuthorityMapper {
    BasicColumn[] SELECT_LIST = BasicColumn.columnList(ID, AUTHORITY_NAME, AUTHORITY_CODE, AUTHORITY_DESC, IS_DELETED, LOCK_VERSION, CREATE_TIME, CREATE_ID, UPDATE_TIME, UPDATE_ID);

    /**
     * <p>方法描述：查询sys_authority表数据数量</p>
     *
     * @param selectStatement select语句
     * @return sys_authority表数据数量
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    /**
     * <p>方法描述：删除sys_authority表数据</p>
     *
     * @param deleteStatement delete语句
     * @return sys_authority表被删除的数据数量
     */
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    /**
     * <p>方法描述：向sys_authority表添加记录</p>
     *
     * @param insertStatement insert语句
     * @return sys_authority表记录添加成功的数量
     */
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<AuthorityDo> insertStatement);

    /**
     * <p>方法描述：向sys_authority表添加多条记录</p>
     *
     * @param multipleInsertStatement sys_authority表主键
     * @return sys_authority表记录添加成功的数量
     */
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<AuthorityDo> multipleInsertStatement);

    /**
     * <p>方法描述：查询sys_authority表一条数据</p>
     *
     * @param selectStatement select语句
     * @return sys_authority表符合条件的一条数据
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("AuthorityDoResult")
    Optional<AuthorityDo> selectOne(SelectStatementProvider selectStatement);

    /**
     * <p>方法描述：查询sys_authority表多条数据</p>
     *
     * @param selectStatement select语句
     * @return sys_authority表符合条件的多条数据
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="AuthorityDoResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="authority_name", property="authorityName", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="authority_code", property="authorityCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="authority_desc", property="authorityDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_deleted", property="isDeleted", jdbcType=JdbcType.TINYINT),
        @Result(column="lock_version", property="lockVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_id", property="createId", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_id", property="updateId", jdbcType=JdbcType.BIGINT)
    })
    List<AuthorityDo> selectMany(SelectStatementProvider selectStatement);

    /**
     * <p>方法描述：修改sys_authority表数据</p>
     *
     * @param updateStatement update语句
     * @return sys_authority表被修改的数据数量
     */
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    /**
     * <p>方法描述：查询sys_authority表数据数量</p>
     *
     * @param completer Sql补全器
     * @return sys_authority表数据数量
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, AUTHORITY_DO, completer);
    }

    /**
     * <p>方法描述：删除sys_authority表数据</p>
     *
     * @param completer Sql补全器
     * @return sys_authority表被删除的数据数量
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, AUTHORITY_DO, completer);
    }

    /**
     * <p>方法描述：根据主键删除sys_authority表记录</p>
     *
     * @param id sys_authority表主键
     * @param authorityName sys_authority表主键
     * @return sys_authority表符合条件且删除成功的数量
     */
    default int deleteByPrimaryKey(Long id, String authorityName) {
        return delete(c -> 
            c.where(ID, isEqualTo(id))
            .and(AUTHORITY_NAME, isEqualTo(authorityName))
        );
    }

    /**
     * <p>方法描述：向sys_authority表添加记录</p>
     *
     * @param record sys_authority表实体
     * @return sys_authority表记录添加成功的数量
     */
    default int insert(AuthorityDo record) {
        return MyBatis3Utils.insert(this::insert, record, AUTHORITY_DO, c ->
            c.map(ID).toProperty("id")
            .map(AUTHORITY_NAME).toProperty("authorityName")
            .map(AUTHORITY_CODE).toProperty("authorityCode")
            .map(AUTHORITY_DESC).toProperty("authorityDesc")
            .map(IS_DELETED).toProperty("isDeleted")
            .map(LOCK_VERSION).toProperty("lockVersion")
            .map(CREATE_TIME).toProperty("createTime")
            .map(CREATE_ID).toProperty("createId")
            .map(UPDATE_TIME).toProperty("updateTime")
            .map(UPDATE_ID).toProperty("updateId")
        );
    }

    /**
     * <p>方法描述：向sys_authority表添加多条记录</p>
     *
     * @param records sys_authority表实体集合
     * @return sys_authority表记录添加成功的数量
     */
    default int insertMultiple(Collection<AuthorityDo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, AUTHORITY_DO, c ->
            c.map(ID).toProperty("id")
            .map(AUTHORITY_NAME).toProperty("authorityName")
            .map(AUTHORITY_CODE).toProperty("authorityCode")
            .map(AUTHORITY_DESC).toProperty("authorityDesc")
            .map(IS_DELETED).toProperty("isDeleted")
            .map(LOCK_VERSION).toProperty("lockVersion")
            .map(CREATE_TIME).toProperty("createTime")
            .map(CREATE_ID).toProperty("createId")
            .map(UPDATE_TIME).toProperty("updateTime")
            .map(UPDATE_ID).toProperty("updateId")
        );
    }

    /**
     * <p>方法描述：向sys_authority表添加记录（为null的字段不进行处理）</p>
     *
     * @param record sys_authority表实体
     * @return sys_authority表记录添加成功的数量
     */
    default int insertSelective(AuthorityDo record) {
        return MyBatis3Utils.insert(this::insert, record, AUTHORITY_DO, c ->
            c.map(ID).toPropertyWhenPresent("id", record::getId)
            .map(AUTHORITY_NAME).toPropertyWhenPresent("authorityName", record::getAuthorityName)
            .map(AUTHORITY_CODE).toPropertyWhenPresent("authorityCode", record::getAuthorityCode)
            .map(AUTHORITY_DESC).toPropertyWhenPresent("authorityDesc", record::getAuthorityDesc)
            .map(IS_DELETED).toPropertyWhenPresent("isDeleted", record::getIsDeleted)
            .map(LOCK_VERSION).toPropertyWhenPresent("lockVersion", record::getLockVersion)
            .map(CREATE_TIME).toPropertyWhenPresent("createTime", record::getCreateTime)
            .map(CREATE_ID).toPropertyWhenPresent("createId", record::getCreateId)
            .map(UPDATE_TIME).toPropertyWhenPresent("updateTime", record::getUpdateTime)
            .map(UPDATE_ID).toPropertyWhenPresent("updateId", record::getUpdateId)
        );
    }

    /**
     * <p>方法描述：查询sys_authority表一条数据</p>
     *
     * @param completer Sql补全器
     * @return sys_authority表符合条件的一条数据
     */
    default Optional<AuthorityDo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, SELECT_LIST, AUTHORITY_DO, completer);
    }

    /**
     * <p>方法描述：查询sys_authority表添记录</p>
     *
     * @param completer Sql补全器
     * @return sys_authority表符合条件的记录列表
     */
    default List<AuthorityDo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, SELECT_LIST, AUTHORITY_DO, completer);
    }

    /**
     * <p>方法描述：查询sys_authority表添记录(去重))</p>
     *
     * @param completer Sql补全器
     * @return sys_authority表符合条件且去重的记录列表
     */
    default List<AuthorityDo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, SELECT_LIST, AUTHORITY_DO, completer);
    }

    /**
     * <p>方法描述：根据指定主键获取一条sys_authority表记录</p>
     *
     * @param id sys_authority表主键
     * @param authorityName sys_authority表主键
     * @return sys_authority表符合条件的记录
     */
    default Optional<AuthorityDo> selectByPrimaryKey(Long id, String authorityName) {
        return selectOne(c ->
            c.where(ID, isEqualTo(id))
            .and(AUTHORITY_NAME, isEqualTo(authorityName))
        );
    }

    /**
     * <p>方法描述：修改sys_authority表数据</p>
     *
     * @param completer Sql补全器
     * @return sys_authority表被修改的数据数量
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, AUTHORITY_DO, completer);
    }

    /**
     * <p>方法描述：向DSL对象添加修改所有列</p>
     *
     * @param record sys_authority表实体
     * @param dsl dsl对象
     * @return 处理后的DSL
     */
    static UpdateDSL<UpdateModel> updateAllColumns(AuthorityDo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(ID).equalTo(record::getId)
                .set(AUTHORITY_NAME).equalTo(record::getAuthorityName)
                .set(AUTHORITY_CODE).equalTo(record::getAuthorityCode)
                .set(AUTHORITY_DESC).equalTo(record::getAuthorityDesc)
                .set(IS_DELETED).equalTo(record::getIsDeleted)
                .set(LOCK_VERSION).equalTo(record::getLockVersion)
                .set(CREATE_TIME).equalTo(record::getCreateTime)
                .set(CREATE_ID).equalTo(record::getCreateId)
                .set(UPDATE_TIME).equalTo(record::getUpdateTime)
                .set(UPDATE_ID).equalTo(record::getUpdateId);
    }

    /**
     * <p>方法描述：向DSL对象添加修改所有列（为null的字段不进行处理）</p>
     *
     * @param record sys_authority表实体
     * @param dsl dsl对象
     * @return 处理后的DSL
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(AuthorityDo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(ID).equalToWhenPresent(record::getId)
                .set(AUTHORITY_NAME).equalToWhenPresent(record::getAuthorityName)
                .set(AUTHORITY_CODE).equalToWhenPresent(record::getAuthorityCode)
                .set(AUTHORITY_DESC).equalToWhenPresent(record::getAuthorityDesc)
                .set(IS_DELETED).equalToWhenPresent(record::getIsDeleted)
                .set(LOCK_VERSION).equalToWhenPresent(record::getLockVersion)
                .set(CREATE_TIME).equalToWhenPresent(record::getCreateTime)
                .set(CREATE_ID).equalToWhenPresent(record::getCreateId)
                .set(UPDATE_TIME).equalToWhenPresent(record::getUpdateTime)
                .set(UPDATE_ID).equalToWhenPresent(record::getUpdateId);
    }

    /**
     * <p>方法描述：根据主键更新sys_authority表记录</p>
     *
     * @param record sys_authority表实体
     * @return sys_authority表符合条件且更新成功的记录数量
     */
    default int updateByPrimaryKey(AuthorityDo record) {
        return update(c ->
            c.set(AUTHORITY_CODE).equalTo(record::getAuthorityCode)
            .set(AUTHORITY_DESC).equalTo(record::getAuthorityDesc)
            .set(IS_DELETED).equalTo(record::getIsDeleted)
            .set(LOCK_VERSION).equalTo(record::getLockVersion)
            .set(CREATE_TIME).equalTo(record::getCreateTime)
            .set(CREATE_ID).equalTo(record::getCreateId)
            .set(UPDATE_TIME).equalTo(record::getUpdateTime)
            .set(UPDATE_ID).equalTo(record::getUpdateId)
            .where(ID, isEqualTo(record::getId))
            .and(AUTHORITY_NAME, isEqualTo(record::getAuthorityName))
        );
    }

    /**
     * <p>方法描述：根据主键更新sys_authority表记录（为null的字段不进行处理）</p>
     *
     * @param record sys_authority表实体
     * @return sys_authority表符合条件且更新成功的记录数量
     */
    default int updateByPrimaryKeySelective(AuthorityDo record) {
        return update(c ->
            c.set(AUTHORITY_CODE).equalToWhenPresent(record::getAuthorityCode)
            .set(AUTHORITY_DESC).equalToWhenPresent(record::getAuthorityDesc)
            .set(IS_DELETED).equalToWhenPresent(record::getIsDeleted)
            .set(LOCK_VERSION).equalToWhenPresent(record::getLockVersion)
            .set(CREATE_TIME).equalToWhenPresent(record::getCreateTime)
            .set(CREATE_ID).equalToWhenPresent(record::getCreateId)
            .set(UPDATE_TIME).equalToWhenPresent(record::getUpdateTime)
            .set(UPDATE_ID).equalToWhenPresent(record::getUpdateId)
            .where(ID, isEqualTo(record::getId))
            .and(AUTHORITY_NAME, isEqualTo(record::getAuthorityName))
        );
    }
}
```




