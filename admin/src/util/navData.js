import navDataUser from "./navDataUser";


export default [
    { name: "welcome",title: "欢迎",path:"/welcome",component:'Welcome',icon:"menu-icon fa fa-picture-o",id:"welcome-sidebar"},
    /* [{name: "ftype1",title: "门票分类1",path:"/ftype1",component:'Ftype1',icon:"menu-icon fa fa-calendar",id:"ftype1-sidebar"},
     {name: "ftype2",title: "门票分类2",path:"/ftype2",component:'Ftype2',icon:"menu-icon fa fa-calendar",id:"ftype2-sidebar"}],*/
    { name: "sysuser",title: "用户管理",path:"/sysuser",component:'Sysuser',icon:"menu-icon fa fa-calendar",id:"sysuser-sidebar"},
    { name: "ftype",title: "门票分类",path:"/ftype",component:'Ftype',icon:"menu-icon fa fa-picture-o",id:"ftype-sidebar"},
    { name: "news",title: "攻略指南管理",path:"/news",component:'News',icon:"menu-icon fa fa-calendar",id:"news-sidebar"},
    { name: "goods",title: "门票管理",path:"/goods",component:'Goods',icon:"menu-icon fa fa-list",id:"goods-sidebar"},
    { name: "line",title: "线路管理",path:"/line",component:'Line',icon:"menu-icon fa fa-calendar",id:"line-sidebar"},
    { name: "space",title: "景点管理",path:"/space",component:'Space',icon:"menu-icon fa fa-calendar",id:"space-sidebar"},
    { name: "food",title: "饭店管理",path:"/food",component:'Food',icon:"menu-icon fa fa-calendar",id:"food-sidebar"},
    { name: "house",title: "房间管理",path:"/house",component:'House',icon:"menu-icon fa fa-calendar",id:"house-sidebar"},
    { name: "forder",title: "门票订单管理",path:"/forder",component:'forder',icon:"menu-icon fa fa-calendar",id:"forder-sidebar"},
    { name: "forderHouse",title: "酒店订单管理",path:"/forderHouse",component:'forderHouse',icon:"menu-icon fa fa-calendar",id:"forderHouse-sidebar"},
    { name: "forderLine",title: "线路订单管理",path:"/forderLine",component:'forderLine',icon:"menu-icon fa fa-calendar",id:"forderLine-sidebar"},
    { name: "forderFood",title: "饭店预定管理",path:"/forderFood",component:'forderFood',icon:"menu-icon fa fa-calendar",id:"forderFood-sidebar"},
    { name: "forderSpace",title: "景点预定管理",path:"/forderSpace",component:'forderSpace',icon:"menu-icon fa fa-calendar",id:"forderSpace-sidebar"},

    { name: "forderSpaceTj",title: "景点人数统计",path:"/forderSpaceTj",component:'forderSpaceTj',icon:"menu-icon fa fa-calendar",id:"forderSpaceTj-sidebar"},
    { name: "forderSpaceTypeTj",title: "景点的票种类型统计",path:"/forderSpaceTypeTj",component:'forderSpaceTypeTj',icon:"menu-icon fa fa-calendar",id:"forderSpaceTypeTj-sidebar"},
]



