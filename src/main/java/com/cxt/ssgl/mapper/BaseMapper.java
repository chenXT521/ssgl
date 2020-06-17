package com.cxt.ssgl.mapper;

import java.util.List;
import java.util.Map;

public interface BaseMapper {
    int removeDepById(String depId);

    int removeRelDepMenu(String depId);

    List<Map<String,Object>> getDepByName(String depName);

    int addDep(Map<String,Object> map);

    int editDep(Map<String,Object> map);
}
