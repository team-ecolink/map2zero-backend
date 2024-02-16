package com.ecolink.core.map.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class MapContent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String cotValue03;
	private String cotValue04;
	private String cotValue05;
	private String cotValue06;
	private String cotValue07;
	private String cotAddrFullNew;
	private String cotAddrFullOld;
	private String cotTelNo;
	private String cotRegDate;
	private String cotUpdateDate;
	private String cotThemeId;
	private String cotContsId;
	private String cotGuName;
	private String cotDongName;
	private String cotSanName;
	private String cotMasterNo;
	private String cotSlaveNo;
	private String cotExtraName;
	private String cotNationBaseArea;
	private String cotNationPointNumber;
	private String cotCoordData;
	private String cotCoordType;
	private String cotCoordX;
	private String cotCoordY;
	private String cotContsStat;
	private String cotWriter;
	private String cotThemeSubId;
	private String cotExtraData01;
	private String cotExtraData02;
	private String cotMovieUrl;
	private String cotVoiceUrl;
	private String cotContsDetail;
	private String cotImgMainUrl;
	private String cotImgMainUrl2;
	private String cotImgMainUrl3;
	private String cotImgMainUrl4;
	private String cotImgMainUrl5;
	private String cotCoordStyle;
	private String cotLinePattern;
	private String cotLineWeight;
	private String cotLineColor;

	public MapContent(String cotValue03, String cotValue04, String cotValue05, String cotValue06, String cotValue07,
		String cotAddrFullNew, String cotAddrFullOld, String cotTelNo, String cotRegDate, String cotUpdateDate,
		String cotThemeId, String cotContsId, String cotGuName, String cotDongName, String cotSanName,
		String cotMasterNo, String cotSlaveNo, String cotExtraName, String cotNationBaseArea,
		String cotNationPointNumber, String cotCoordData, String cotCoordType, String cotCoordX,
		String cotCoordY, String cotContsStat, String cotWriter, String cotThemeSubId, String cotExtraData01,
		String cotExtraData02, String cotMovieUrl, String cotVoiceUrl, String cotContsDetail,
		String cotImgMainUrl, String cotImgMainUrl2, String cotImgMainUrl3, String cotImgMainUrl4,
		String cotImgMainUrl5, String cotCoordStyle, String cotLinePattern, String cotLineWeight,
		String cotLineColor) {
		this.cotValue03 = cotValue03;
		this.cotValue04 = cotValue04;
		this.cotValue05 = cotValue05;
		this.cotValue06 = cotValue06;
		this.cotValue07 = cotValue07;
		this.cotAddrFullNew = cotAddrFullNew;
		this.cotAddrFullOld = cotAddrFullOld;
		this.cotTelNo = cotTelNo;
		this.cotRegDate = cotRegDate;
		this.cotUpdateDate = cotUpdateDate;
		this.cotThemeId = cotThemeId;
		this.cotContsId = cotContsId;
		this.cotGuName = cotGuName;
		this.cotDongName = cotDongName;
		this.cotSanName = cotSanName;
		this.cotMasterNo = cotMasterNo;
		this.cotSlaveNo = cotSlaveNo;
		this.cotExtraName = cotExtraName;
		this.cotNationBaseArea = cotNationBaseArea;
		this.cotNationPointNumber = cotNationPointNumber;
		this.cotCoordData = cotCoordData;
		this.cotCoordType = cotCoordType;
		this.cotCoordX = cotCoordX;
		this.cotCoordY = cotCoordY;
		this.cotContsStat = cotContsStat;
		this.cotWriter = cotWriter;
		this.cotThemeSubId = cotThemeSubId;
		this.cotExtraData01 = cotExtraData01;
		this.cotExtraData02 = cotExtraData02;
		this.cotMovieUrl = cotMovieUrl;
		this.cotVoiceUrl = cotVoiceUrl;
		this.cotContsDetail = cotContsDetail;
		this.cotImgMainUrl = cotImgMainUrl;
		this.cotImgMainUrl2 = cotImgMainUrl2;
		this.cotImgMainUrl3 = cotImgMainUrl3;
		this.cotImgMainUrl4 = cotImgMainUrl4;
		this.cotImgMainUrl5 = cotImgMainUrl5;
		this.cotCoordStyle = cotCoordStyle;
		this.cotLinePattern = cotLinePattern;
		this.cotLineWeight = cotLineWeight;
		this.cotLineColor = cotLineColor;
	}

}