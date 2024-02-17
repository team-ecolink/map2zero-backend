package com.ecolink.core.map.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentDetailDto {

	@Schema(description = "상점이름", example = "호랑이상점")
	private String cotContsName;

	@Schema(description = "운영시간", example = "매일 11:00-21:30/ 비건 베이커리만 화 휴무")
	private String cotValue03;

	@Schema(description = "취급품목(메뉴)", example = "제로웨이스트 제품, 비건 식품, 리필 세제류, 비건베이커리")
	private String cotValue04;

	@Schema(description = "인스타그램", example = "http://www.instagram.com/zerowaste_jigu")
	private String cotValue05;

	@Schema(description = "제로페이", example = "가능")
	private String cotValue06;

	@Schema(description = "인터넷 쇼핑몰", example = "https://smartstore.naver.com/peaceontable")
	private String cotValue07;

	@Schema(description = "매장 주소 (새 주소)", example = "서울특별시 마포구 성미산로 155")
	private String cotAddrFullNew;

	@Schema(description = "매장 주소 (구 주소)", example = "서울특별시 마포구 연남동 240-23")
	private String cotAddrFullOld;

	@Schema(description = "전화번호", example = "070-7721-5748")
	private String cotTelNo;

	@Schema(description = "등록일", example = "2023-10-31 10:04:59")
	private String cotRegDate;

	@Schema(description = "업데이트 일", example = "2023-10-31 13:02:54")
	private String cotUpdateDate;

	@Schema(description = "테마 ID", example = "11103395")
	private String cotThemeId;

	@Schema(description = "콘텐츠 ID", example = "zerowaste_0004")
	private String cotContsId;

	@Schema(description = "구명", example = "마포구")
	private String cotGuName;

	@Schema(description = "동명", example = "")
	private String cotDongName;

	@Schema(description = "산지 명", example = "")
	private String cotSanName;

	@Schema(description = "주 번지", example = "")
	private String cotMasterNo;

	@Schema(description = "부 번지", example = "")
	private String cotSlaveNo;

	@Schema(description = "나머지 주소", example = "")
	private String cotExtraName;

	@Schema(description = "국가 기초 구역", example = "03958")
	private String cotNationBaseArea;

	@Schema(description = "국가 지점 번호", example = "다사49085184")
	private String cotNationPointNumber;

	@Schema(description = "좌표 정보 (GeoJson)")
	private List<BigDecimal> cotCoordData;

	@Schema(description = "콘텐츠 좌표 타입", example = "1")
	private String cotCoordType;

	@Schema(description = "X 좌표", example = "126.923533416")
	private String cotCoordX;

	@Schema(description = "Y 좌표", example = "37.564525958")
	private String cotCoordY;

	@Schema(description = "사용 유무", example = "1")
	private String cotContsStat;

	@Schema(description = "등록자/수정자", example = "motif77")
	private String cotWriter;

	@Schema(description = "콘텐츠 서브카테고리", example = "4")
	private String cotThemeSubId;

	@Schema(description = "기타 정보 1", example = "")
	private String cotExtraData01;

	@Schema(description = "링크 URL", example = "https://www.jigushop.co.kr/")
	private String cotExtraData02;

	@Schema(description = "동영상 URL", example = "")
	private String cotMovieUrl;

	@Schema(description = "음성파일 URL", example = "")
	private String cotVoiceUrl;

	@Schema(description = "콘텐츠 상세", example = "콘텐츠 상세 내용")
	private String cotContsDetail;

	@Schema(description = "이미지 URL1")
	private String cotImgMainUrl;

	@Schema(description = "이미지 URL2")
	private String cotImgMainUrl2;

	@Schema(description = "이미지 URL3")
	private String cotImgMainUrl3;

	@Schema(description = "이미지 URL4")
	private String cotImgMainUrl4;

	@Schema(description = "이미지 URL5")
	private String cotImgMainUrl5;

	@Schema(description = "코디네이트 스타일")
	private String cotCoordStyle;

	@Schema(description = "라인 패턴", example = "L")
	private String cotLinePattern;

	@Schema(description = "라인 무게", example = "4")
	private String cotLineWeight;

	@Schema(description = "라인 색상", example = "#0000FF")
	private String cotLineColor;

}
