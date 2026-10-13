
package com.example.btvnbuoi2.controller;

import com.example.btvnbuoi2.entity.Group;
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

    // Read: lấy danh sách group
    @GetMapping
    public List<Group> getAll() {
        return groupService.getAll();
    }

    // Read: lấy group theo ID
    @GetMapping("/{id}")
    public Group getById(@PathVariable Integer id) {
        return groupService.getById(id);
    }

    // Create: thêm group
    @PostMapping
    public Group create(@RequestBody Group group) {
        return groupService.create(group);
    }

    // Update: sửa group
    @PutMapping("/{id}")
    public Group update(
            @PathVariable Integer id,
            @RequestBody Group group) {
        return groupService.update(id, group);
    }

    // Delete: xóa group
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        groupService.delete(id);
        return "Xóa group thành công";
    }
}
