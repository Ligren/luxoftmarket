function _GoodsUtil() {

    this.deleteGoods = function(id) {
        if (confirm("Вы уверены ?")) {
            window.location = "/deleteGoods/" + id;
        }
    }
}

var GoodsUtil = new _GoodsUtil();