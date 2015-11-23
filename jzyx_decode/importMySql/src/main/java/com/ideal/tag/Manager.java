package com.ideal.tag;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ideal.tag.entity.Result;
import com.ideal.tag.entity.ResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Manager {
	private static final Logger log = LoggerFactory.getLogger(Manager.class);
	
	private String area;
	private Map<String, Long> areaMap;
	private Map<String, Long> tagMap;
	private String date;
	private String resultDir;
	
	public Manager(String area, Map<String, Long> areaMap, Map<String, Long> tagMap, String date, String resultDir) {
		this.area = area;
		this.areaMap = areaMap;
		this.tagMap = tagMap;
		this.date = date;
		this.resultDir = resultDir;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Map<String, Long> getAreaMap() {
		return areaMap;
	}

	public void setAreaMap(Map<String, Long> areaMap) {
		this.areaMap = areaMap;
	}

	public Map<String, Long> getTagMap() {
		return tagMap;
	}

	public void setTagMap(Map<String, Long> tagMap) {
		this.tagMap = tagMap;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getResultDir() {
		return resultDir;
	}

	public void setResultDir(String resultDir) {
		this.resultDir = resultDir;
	}

	public void process() throws IOException, FileNotFoundException, SQLException {
		Long areaId = areaMap.get(area);
		
        File[] files = new File(resultDir).listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if(pathname.getName().startsWith("_") || pathname.getName().endsWith(".crc")) {
                    return false;
                }
                return true;
            }
        });
		
		for(File file : files) {
			log.info("processing file:" + file.getAbsolutePath());
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
			List<ResultDto> dtos = new ArrayList<>();
			String line = null;
			while((line = br.readLine()) != null) {
				if(StringUtils.isNotEmpty(line)) {
					Result result = new Result().make(line);
					ResultDto rd=parseResult(result, areaId);
					if(rd!=null)
					dtos.add(rd);
				}
			}
			saveResultDto(dtos);
		}
	}
	
	// result --> resultDto
    private static ResultDto parseResult(Result result, Long areaId) throws SQLException {
		try {
			//System.out.println(result.toString());
			ResultDto dto = new ResultDto();
			dto.setAreaId(areaId);
			//System.out.println(Initial.tag.get(result.getLevel() + "\t" + result.getName()));
			//dto.setTagId(Initial.tag.get(result.getLevel() + "\t" + result.getName()));
			dto.setTagId(Initial.tag.get(result.getName()));
			dto.setHeatValue(result.getHeatValue());
			dto.setTagNums((int)(result.getTagNums()));
			return dto;
		}catch (Exception e){
			//e.printStackTrace();
		}
    	return null;
    }
	
	public void saveResultDto(List<ResultDto> dtos) throws SQLException {
		StringBuilder builder = null;
		String sql = "";
		Connection conn = DBConnectionManager.instance().getConnection();
		for(ResultDto dto : dtos) {			
			// UPDATE tb_analysis SET tag_num=1,percent=10,heat_value=10.0 WHERE area_id=35 AND tag_id=33
			builder = new StringBuilder("update tb_analysis set tag_num=" + dto.getTagNums() + ",percent="+ dto.getPercent() +
					",heat_value=" + dto.getHeatValue() + " where area_id=" + dto.getAreaId() + " and tag_id=" + dto.getTagId());
			sql = builder.toString();
			System.out.println(sql);
			DBOperator.insert(conn, sql);
		}
	}


}
