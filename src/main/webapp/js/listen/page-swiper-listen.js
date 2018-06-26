var prevIndex = 0, activeIndex = 0, startTime = new Date().getTime(), pageInfo = [], isInitArr = true, pageData = {};
var PageSwiperComm = PageSwiperComm || {};
PageSwiperComm = {
    init: function (b, a, c) {
        this.ele = b;
        this.third_params = a;
        this.isShare = c || 0;
        this.setPageData()
    }, setPageData: function () {
        var a = this, e = this.third_params.type, f = this.third_params.d,
            b = "/message/share/getShareInfo.action?type=" + e + "&d=" + f;
        if ("doc" == e) {
            var c = f.params;
            pageData = {
                title: f.uploadSimpleName,
                imgUrl: YT.server + "/module/web/upload/" + c.imgUrl,
                pageCount: c.pageCount,
                width: c.width,
                height: c.height,
                size: "",
                third_params: {}
            };
            a.initDom()
        } else {
            $.get(b, function (g) {
                if (200 == g.status) {
                    var d = JSON.parse(g.object);
                    switch (e) {
                        case"chuangkit":
                            if (d.body.error) {
                                a.resetDom()
                            } else {
                                var h = d.body.bean;
                                pageData = {
                                    title: h.title,
                                    imgUrl: h.imgUrl,
                                    pageCount: h.pageCount,
                                    width: h.width,
                                    height: h.height,
                                    size: "@780w",
                                    third_params: a.third_params
                                };
                                a.initDom()
                            }
                            break
                    }
                } else {
                    a.resetDom()
                }
            })
        }
    }, initDom: function () {
        var a = pageData.title;
        if (window.module) {
            a = module.data.title || a
        }
        $("#title").val(a);
        if (this.isShare) {
            $("title").html(a + "");
            $('meta[name="description"]').attr("content", a)
        }
        $(this.ele).children().remove();
        this.createSwiperHtml()
    }, resetDom: function () {
        $(this.ele).children().remove();
        pageData = {}
    }, createSwiperHtml: function () {
        var c = "";
        c += '<div class="swiper-container" id="swiperWrap">';
        c += '<div class="swiper-wrapper">';
        for (var b = 0; b < pageData.pageCount; b++) {
            var a = pageData.imgUrl;
            if ("doc" == this.third_params.type) {
                a = a.replace("pict-1", "pict-" + (b + 1))
            } else {
                a = b ? a + "_" + b : a
            }
            c += '<div class="swiper-slide">';
            c += '<div class="swiper-zoom-container">';
            c += '<img class="swiper-lazy" data-src="' + a + pageData.size + '"/>';
            c += "</div>";
            c += "</div>"
        }
        c += "</div>";
        c += '<div class="swiper-pagination"></div>';
        c += "</div>";
        $(this.ele).append(c);
        this.setPageSize();
        this.initSwiper()
    }, initSwiper: function () {
        var b = this;
        var a = new Swiper("#swiperWrap", {
            lazy: {loadPrevNext: true, loadPrevNextAmount: 2},
            loop: true,
            zoom: {toggle: false},
            direction: "vertical",
            pagination: {el: ".swiper-pagination", clickable: true},
            on: {
                init: function () {
                }, resize: function () {
                    b.setPageSize()
                }, slideChange: function () {
                    if ((this.previousIndex - 1) < (this.slides.length - 2)) {
                        prevIndex = this.previousIndex && this.previousIndex - 1;
                        activeIndex = this.realIndex;
                        if (!b.isShare && window.module) {
                            var g = module.data.labelNames;
                            var k = $("#label-view .box"), j = "";
                            if (g && g.length) {
                                var d = g[activeIndex];
                                for (var h in d) {
                                    var c = d[h];
                                    j += MessageComm.page.createLabelHtml_del(h, c.id, c.name)
                                }
                            }
                            k.html(j)
                        }
                        if (isInitArr) {
                            for (var h = 0; h < this.slides.length - 2; h++) {
                                pageInfo[h] = 0
                            }
                            isInitArr = false
                        }
                        var e = new Date().getTime();
                        var f = Math.round((e - startTime) / 1000);
                        f = f <= 0 ? 1 : f;
                        if (pageInfo[prevIndex]) {
                            pageInfo[prevIndex] = pageInfo[prevIndex] + f
                        } else {
                            pageInfo[prevIndex] = f
                        }
                        startTime = new Date().getTime()
                    }
                }
            }
        });
        window.prevPage = function () {
            a.slidePrev()
        };
        window.nextPage = function () {
            a.slideNext()
        };
        window.slideTo = function (c) {
            a.slideTo(c, 1000, false)
        }
    }, setPageSize: function () {
        var f = $(this.ele).width(), a = $(this.ele).height(), e = +pageData.width, i = +pageData.height, b = f, d = a;
        if (e > i) {
            b = f > 780 ? 780 : f;
            var g = Math.round(b / e * i);
            d = a > g ? g : a
        } else {
            d = a > i ? i : a;
            var c = Math.round(d / i * e);
            b = f > c ? c : f
        }
        $("#swiperWrap").css({"width": b + "px", "height": "100%"})
    }
};