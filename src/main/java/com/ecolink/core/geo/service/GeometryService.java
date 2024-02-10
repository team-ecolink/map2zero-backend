package com.ecolink.core.geo.service;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import com.ecolink.core.store.dto.request.MapQueryRequest;

@Service
public class GeometryService {
	private final GeometryFactory geometryFactory;

	public GeometryService() {
		this.geometryFactory = new GeometryFactory();
	}

	public Point getPoint(MapQueryRequest mapQueryRequest) {
		return geometryFactory.createPoint(new Coordinate(mapQueryRequest.getX(), mapQueryRequest.getY()));
	}

}
