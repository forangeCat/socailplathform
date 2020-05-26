package app.generator.mapper;

import app.generator.modol.Station;
import app.generator.modol.StationExample;
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

public interface StationMapper {
    @SelectProvider(type=StationSqlProvider.class, method="countByExample")
    int countByExample(StationExample example);

    @DeleteProvider(type=StationSqlProvider.class, method="deleteByExample")
    int deleteByExample(StationExample example);

    @Delete({
        "delete from station",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into station (id, station_name, ",
        "station_introduction, category, ",
        "create_time)",
        "values (#{id,jdbcType=INTEGER}, #{stationName,jdbcType=VARCHAR}, ",
        "#{stationIntroduction,jdbcType=VARCHAR}, #{category,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    int insert(Station record);

    @InsertProvider(type=StationSqlProvider.class, method="insertSelective")
    int insertSelective(Station record);

    @SelectProvider(type=StationSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="station_name", property="stationName", jdbcType=JdbcType.VARCHAR),
        @Result(column="station_introduction", property="stationIntroduction", jdbcType=JdbcType.VARCHAR),
        @Result(column="category", property="category", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Station> selectByExample(StationExample example);

    @Select({
        "select",
        "id, station_name, station_introduction, category, create_time",
        "from station",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="station_name", property="stationName", jdbcType=JdbcType.VARCHAR),
        @Result(column="station_introduction", property="stationIntroduction", jdbcType=JdbcType.VARCHAR),
        @Result(column="category", property="category", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Station selectByPrimaryKey(Integer id);

    @UpdateProvider(type=StationSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Station record, @Param("example") StationExample example);

    @UpdateProvider(type=StationSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Station record, @Param("example") StationExample example);

    @UpdateProvider(type=StationSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Station record);

    @Update({
        "update station",
        "set station_name = #{stationName,jdbcType=VARCHAR},",
          "station_introduction = #{stationIntroduction,jdbcType=VARCHAR},",
          "category = #{category,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Station record);
}