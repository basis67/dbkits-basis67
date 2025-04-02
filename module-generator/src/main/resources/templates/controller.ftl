/**
 * ${tableComment} 控制器类
 *
 * @author ${author}
 */
@RestController
@RequestMapping("/api/${className?uncap_first}")
@Tag(name = "${tableComment!''}管理接口")
@RequiredArgsConstructor
public class ${className}Controller {

    private final ${className}Service ${className?uncap_first}Service;

    @PostMapping
    @Operation("创建${tableComment}")
    public ApiResult<Long> create(@RequestBody @Valid ${className}CreateReq req) {
        return Result.success(${className?uncap_first}Service.create(req));
    }

    @GetMapping("/{id}")
    @Operation("获取${tableComment}详情")
    public ApiResult<${className}Resp> getById(@PathVariable Long id) {
        return Result.success(${className?uncap_first}Service.getById(id));
    }

    // 其他方法...
}