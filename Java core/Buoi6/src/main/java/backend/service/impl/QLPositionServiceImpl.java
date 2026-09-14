package backend.service.impl;

import backend.responsitory.IQLDepartmentResponsitory;
import backend.responsitory.IQLPositionResponsitory;
import backend.responsitory.impl.QLDepartmentResponsitory;
import backend.responsitory.impl.QLPositionResponsitory;
import backend.service.IQLPositionService;
import entity.Department;
import entity.Position;
import entity.PositionName;

import java.util.List;

public class QLPositionServiceImpl implements IQLPositionService {
    private IQLPositionResponsitory repository;
    public QLPositionServiceImpl() {
        repository = new QLPositionResponsitory();// khoi tao gia tri
    }
    @Override
    public List<Position> getPositions() {
        List<Position> positions = repository.getPositions();
        return positions;
    }

    @Override
    public boolean suaPositionNameTheoId(Integer positionId, PositionName positionName) {
        return repository.suaPositionNameTheoId(positionId,positionName);
    }

    @Override
    public boolean xoaPositionTheoId(Integer positionId) {
        return repository.xoaPositionTheoId(positionId);
    }

    @Override
    public boolean themPosition(PositionName positionName) {
        return repository.themPosition(positionName);
    }
}
