package com.lianjia.data.clusterportal.util;


import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by zhaoguoxian on 2016/12/26.
 */
public interface ClusterMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
