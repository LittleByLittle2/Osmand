package net.osmand.plus.measurementtool.command;

import net.osmand.plus.GPXUtilities.WptPt;
import net.osmand.plus.measurementtool.MeasurementToolLayer;

import java.util.LinkedList;
import java.util.List;

public class ClearPointsCommand extends MeasurementModeCommand {

	private List<WptPt> points;
	private List<WptPt> snappedToRoadPoints;

	public ClearPointsCommand(MeasurementToolLayer measurementLayer) {
		this.measurementLayer = measurementLayer;
	}

	@Override
	public boolean execute() {
		List<WptPt> pts = measurementLayer.getMeasurementPoints();
		List<WptPt> snappedPts = measurementLayer.getSnappedToRoadPoints();
		points = new LinkedList<>(pts);
		snappedToRoadPoints = new LinkedList<>(snappedPts);
		pts.clear();
		snappedPts.clear();
		measurementLayer.refreshMap();
		return true;
	}

	@Override
	public void undo() {
		measurementLayer.getMeasurementPoints().addAll(points);
		measurementLayer.getSnappedToRoadPoints().addAll(snappedToRoadPoints);
		measurementLayer.refreshMap();
	}

	@Override
	public void redo() {
		measurementLayer.getMeasurementPoints().clear();
		measurementLayer.getSnappedToRoadPoints().clear();
		measurementLayer.refreshMap();
	}
}
