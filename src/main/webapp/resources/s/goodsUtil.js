function _GoodsUtil() {

    this.deleteGood = function(id) {
        if (confirm("Вы уверены ?")) {
            window.location = "/deleteGood/" + id;
        }
    }
}

var GoodsUtil = new _GoodsUtil();