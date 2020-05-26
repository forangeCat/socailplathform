package app.generator.mapper;

import app.generator.modol.GramoPhone;
import app.generator.modol.GramoPhoneExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface GramoPhoneMapper {
    @SelectProvider(type=GramoPhoneSqlProvider.class, method="countByExample")
    int countByExample(GramoPhoneExample example);

    @DeleteProvider(type=GramoPhoneSqlProvider.class, method="deleteByExample")
    int deleteByExample(GramoPhoneExample example);

    @Delete({
        "delete from gramophone",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into gramophone (id, content, ",
        "time, star)",
        "values (#{id,jdbcType=INTEGER}, #{content,jdbcType=VARCHAR}, ",
        "#{time,jdbcType=TIMESTAMP}, #{star,jdbcType=INTEGER})"
    })
    int insert(GramoPhone record);

    @InsertProvider(type=GramoPhoneSqlProvider.class, method="insertSelective")
    int insertSelective(GramoPhone record);

    @SelectProvider(type=GramoPhoneSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="star", property="star", jdbcType=JdbcType.INTEGER)
    })
    List<GramoPhone> selectByExample(GramoPhoneExample example);

    @Select({
        "select",
        "id, content, time, star",
        "from gramophone",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="star", property="star", jdbcType=JdbcType.INTEGER)
    })
    GramoPhone selectByPrimaryKey(Integer id);

    @UpdateProvider(type=GramoPhoneSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") GramoPhone record, @Param("example") GramoPhoneExample example);

    @UpdateProvider(type=GramoPhoneSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") GramoPhone record, @Param("example") GramoPhoneExample example);

    @UpdateProvider(type=GramoPhoneSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(GramoPhone record);

    @Update({
        "update gramophone",
        "set content = #{content,jdbcType=VARCHAR},",
          "time = #{time,jdbcType=TIMESTAMP},",
          "star = #{star,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(GramoPhone record);
}