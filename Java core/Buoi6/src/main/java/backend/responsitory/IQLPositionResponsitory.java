package backend.responsitory;

import entity.Department;
import entity.Position;
import entity.PositionName;

import java.util.List;

public interface IQLPositionResponsitory {
    List<Position> getPositions();
    boolean suaPositionNameTheoId(Integer positionId, PositionName positionName);
    boolean xoaPositionTheoId(Integer positionId);
    boolean themPosition(PositionName positionName);
    boolean isPositionExist(int id);
}
