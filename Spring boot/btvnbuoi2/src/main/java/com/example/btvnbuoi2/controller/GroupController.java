package com.example.btvnbuoi2.controller;
import com.example.btvnbuoi2.dto.GroupDTO;
import com.example.btvnbuoi2.form.GroupForm;
import com.example.btvnbuoi2.service.IGroupService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private final IGroupService groupService;
    public GroupController(IGroupService groupService) {
        this.groupService = groupService;
    }
    @GetMapping
    public List<GroupDTO> getAll() {
        return groupService.getAll();
    }

    @GetMapping("/{id}")
    public GroupDTO getById(@PathVariable Integer id) {
        return groupService.getById(id);
    }

    @PostMapping
    public GroupDTO create(@RequestBody GroupForm form) {
        return groupService.create(form);
    }

    @PutMapping("/{id}")
    public GroupDTO update(
            @PathVariable Integer id,
            @RequestBody GroupForm form) {
        return groupService.update(id, form);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        groupService.delete(id);
        return "Xóa group thành công";
    }
}
