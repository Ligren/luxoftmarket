function _GoodsUtil() {

    this.deleteGoods = function(id) {
        if (confirm("�� ������� ?")) {
            window.location = "/deleteGoods/" + id;
        }
    }
}

var GoodsUtil = new _GoodsUtil();