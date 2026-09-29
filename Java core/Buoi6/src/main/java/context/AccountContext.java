package context;

import entity.Department;
import entity.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountContext {

    private Set<String> setUsername;
    private Set<String> setEmail;
    private Map<Integer, Department> mapByDepartmentId;
    private Map<Integer, Position> mapByPositionId;

}