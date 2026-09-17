package backend.controller;

import backend.service.IQLDepartmentService;
import backend.service.IQLPositionService;
import backend.service.impl.QLDepartmentServiceImpl;
import backend.service.impl.QLPositionServiceImpl;
import entity.Department;
import entity.Position;
import entity.PositionName;

import java.util.List;

public class QLPostitionController {
    private IQLPositionService service;

    public QLPostitionController() {
        service = new QLPositionServiceImpl();
    }

    public List<Position> getPositions() {
        List<Position> positions = service.getPositions();
        return positions;
    }

    public boolean xoaPositionTheoId(Integer positionId) {
        return service.xoaPositionTheoId(positionId);
    }

    public boolean suaPositionNameTheoId(Integer positionId, PositionName positionName) {
        return service.suaPositionNameTheoId(positionId, positionName);
    }

    public boolean themPosition(PositionName positionName) {

        return service.themPosition(positionName);
    }
    public boolean isPositionExist(int id){
        return service.isPositionExist(id);
    }
}
