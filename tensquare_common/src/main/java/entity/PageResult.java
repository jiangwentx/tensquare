package entity;

import java.util.List;

/**
 * @program: tensquare_parent
 * @description:
 * @author: Mr.Jiang
 * @create: 2019-02-16 14:20
 **/

public class PageResult<T> {
    private Long total;
    private List<T> rows;

    public PageResult() {
    }

    public PageResult(Long total, List<T> rows) {
        super();
        this.total = total;  //记录总条数
        this.rows = rows;   //按当前页和每页大小得到的数据
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}

