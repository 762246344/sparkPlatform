package com.spark.platform.util;


import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by zhaoguoxian on 2016/12/26.
 */
public interface MyMapper<T> extends tk.mybatis.mapper.common.Mapper<T>, MySqlMapper<T> {
}
