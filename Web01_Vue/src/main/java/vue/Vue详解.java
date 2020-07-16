package vue;

/*						Vue详解
	
	1.VueJS介绍
		1.Vue.js是一个构建数据驱动的 web 界面的渐进式框架。Vue.js 的目标是通过尽可能简单的 API 实现响应的数据绑
		  定和组合的视图组件。它不仅易于上手，还便于与第三方库或既有项目整合。
		  
		2.官网:https://cn.vuejs.org/

	2.MVVM模式
		1.MVVM是Model-View-ViewModel的简写。它本质上就是MVC 的改进版。MVVM 就是将其中的View 的状态和行为
		抽象化，让我们将视图 UI 和业务逻辑分开。
		
		2.MVVM模式和MVC模式一样，主要目的是分离视图（View）和模型（Model）

		3.Vue.js 是一个提供了 MVVM 风格的双向数据绑定的 Javascript 库，专注于View 层。它的核心是 MVVM 中的 VM，
		也就是 ViewModel。 ViewModel负责连接 View 和 Model，保证视图和数据的一致性，这种轻量级的架构让前端
		开发更加高效、便捷


	3.Vue的使用
		1.script：引入vue.js文件
		2.script：	1.创建 Vue 的实例，绑定标签、数据(model)、方法、钩子函数		--注意：创建 Vue 对象，首字母要大写！
						1. Vue中 this：代表 Vue的实例。
		
		3.html：		(指令中可以写：数据、函数、JS表达式)
					1.使用 插值表达式 --- 展示 model中数据：	{{num}}					--弊端：网速差时会出现插值闪烁，因为script代码加载完才显示
					 .使用 v-text 指令 展示text数据：	<span v-text="name"></span>
					 .使用 v-html 指令 展示html数据：	<span v-html="word"></span>
					 
					2.使用 v-model 指令 --- 双向绑定 model数据：	
						<input type="text" v-model="name">				--用户可输入的标签都可以使用该指令
					
					3.使用 @事件(v-on:事件) 指令 --- 事件绑定 JS表达式或函数:	
						<button @click="num++">+</button>				--绑定JS表达式，'@事件' 是 'v-on:事件' 的简化写法.
						<button @click="sayhi('都好')">打招呼</button>	--绑定函数
						
					4.使用 @事件.stop 指令 --- 阻止事件冒泡(事件发生后，从里向外传播)：
						<button @click.stop="click('button')">点击按钮</button>

					 .使用 @事件.prevent 指令 --- 阻止默认事件发生(如跳转)：
						<a href="http://www.baidu.com" @click.prevent="click('百度')">百度</a>
						
					5.使用 v-for 指令 --- 遍历 数组(对象 , 索引)：
						<li v-for="(p,i) in peoples">
							<span v-text="p.name + ',' + p.gender + ',' + p.age"></span>
						</li>
						
					 .使用 v-for 指令 --- 遍历 对象(value , key , 索引)：
						<li v-for="(v,k,i) in peoples[0]">
							<span v-text="k+':'+v"></span>
						</li>
						
					 .使用 v-for 指令 --- 遍历 数字：
						<li v-for="i in 5">
							<span v-text="i"></span>
						</li>
						
					 .使用 :key 指令 --- 数据变更时，提高渲染效率(只渲染变更数据)：
					 	<li v-for="(p,i) in peoples" :key="i">
							<span v-text="p.name"></span>
						</li>
						
					6.使用 v-if 指令 --- 结果为true时，所在元素才渲染：(显示则有标签,不显示则无标签)		--显示一次的数据，用v-if
						<h1 v-if="show">你好</h1>
						
					 .使用 v-if / v-else / v-else-if 指令 --- 结合 v-for 使用：
					 	<li v-for="i in 9">
							<span v-if="i < 5" v-text="'小于五的数：'+i" style="background-color: orange;"></span>
							<span v-else-if="i > 5" v-text="'大于五的数：'+i" style="background-color:gray;"></span>
							<span v-else v-text="'等于五的数：'+i"></span>		--中间不可有其他标签
						</li>
						
					7.使用 v-show 指令 --- 结果为true时，所在元素才渲染：(通过css样式 display来显示)	--多次改变显示的数据，用v-show
						<h1 v-show="show">你好</h1>
						
					8.使用 v-bind(:属性) 指令 --- html属性 绑定数据：
						<div v-bind:class="color"></div>
						
					 .使用 v-bind 指令 --- 传入json对象 切换 class值：
					 	<div v-bind:class="{blue: showColor, red: !showColor}"></div>
						<button @click="showColor = !showColor">更换背景色</button>
						
					9.computed 计算属性：
						0.Vue中 data中定义 birthday.
						1.Vue中定义 计算属性：	
												computed:{
													birth : function(){		// 方法执行后存入birth属性，必须返回结果
											            const d = new Date(this.birthday);
											            return d.getFullYear() + "-" + d.getMonth() + "-" + d.getDay();
											        }
												}
											
						2.html中使用数据birth:
												{{birth}}
						
						
					10.watch 监控一个值的变化：
						0.Vue中 data中定义 watchValue.
						1.Vue中定义 watch：
												watch : {
													num : function(newVal, oldVal) {	//浅监控，监控num的变化(新值、旧值)
														console.log(newVal + "," + oldVal);
													},
													watchValue:{
														deep:true,
														handler: function(newVal){		//深度监控，监控对象的变化(新对象)
															console.log(newVal);
														}
													}
												}
					
					
				
			例:		<script>
						new Vue({
                            el : "#app",
                            data : {
                                name : "Vue啊",
                                num : 1,
                                message : "你们好！",
                                word : "<h1>世界和平</h1>",
                                dinner : [],
                                show : true,
                                color : "blue",
                                showColor : true,
                                birthday : 152468466666,
                                watchValue : {
                                    name : "jack",
                                    age : 20,
                                },
                                peoples : [ {
                                    name : '小亮',
                                    gender : '男',
                                    age : 20
                                }, {
                                    name : '小红',
                                    gender : '女',
                                    age : 18
                                }, {
                                    name : '小飞',
                                    gender : '男',
                                    age : 19
                                } ]
                            },
                            methods : {
                                sayhi : function(msg) {
                                    this.message = msg;
                                },
                                click : function(msg) {
                                    console.log(msg);
                                }
                            },
                            computed : {
                                birth : function() {
                                    console.log("创建birth属性");
                                    const d = new Date(this.birthday);
                                    return d.getFullYear() + "年" + d.getMonth() + "月" + d.getDay()
                                            + "日";
                                }
                            },
                            watch : {
                                num : function(newVal, oldVal) {
                                    console.log(newVal + "," + oldVal);
                                },
                                watchValue : {
                                    deep : true,
                                    handler : function(newVal) {
                                        console.log(newVal.name + "," + newVal.age);
                                    }
                                }
                            },
                            created : function() {
                                console.log("Vue实例创建了");
                            },
                        });
					</script>
	
	4.Vue生命周期
		1.Vue 实例初始化过程：创建实例(created)，装载模板(mounted)，渲染模板(updated)等。
		
		2.Vue为生命周期中的每个状态都设置了钩子函数（监听函数），Vue实例 处于不同时期时，对应的函数就会触发。(比如：在Vue中定义一个created函数)
			beforeCreate, created, beforeMount, mounted, beforeUpdate, updated, beforeDestroy, destroyed
		
        
    5.按键修饰符(按键事件)
        @keyup.enter
              .tab
              .delete (捕获 "删除" 和 "退格" 键)
              .esc
              .space
              .up
              .down
              .left
              .right
              .ctrl
              .alt
              .shift
              .meta

              
    6.组件化：
        0.组件化：将页面的不同部分拆分成独立的组件，组合使用。
            1.组件是Vue实例，定义时接收：data、methods、生命周期函数等。
            2.组件无el属性，不会绑定标签。
            3.组件有template属性，渲染需要html模板，值为HTML模板(只能有一个根标签)。
            4.data不是对象，而是一个函数。在data函数，返回的对象中 定义数据。
            5.组件渲染的template，只能使用组件中的data数据。
        
        
        1.全局组件：
            1.全局组件只要被加载，其他Vue对象即可使用它。
            
            2.定义全局组件：
                Vue的静态方法component("",{});      --1.组件名称、2.组件参数 
                    
            3.定义：
                1.<script>中定义。
                    <script type="text/javascript">
                        Vue.component("comp",{
                            template : "<div><h2 style='display: inline-block;'>全局组件一：{{num}}</h2><button @click='num++'>点击计数</button></div>",
                            data : function(){
                                return {
                                    num : 1,
                                };
                            },
                        });
                    </script>
                    
            4.使用：
                1.html中，id作为标签。
                2.每个组件之间数据独立。
                    <comp></comp>
                    
                    
        2.局部组件：
            1.局部组件就是一个对象，在需要使用的Vue对象中注册，局部组件只能在当前的Vue实例中使用(当前Vue的绑定标签中使用)。
            
            2.定义局部组件：
                1.<script>中定义。
                    <script>
                        const comp2 = {
                            template : "<div><h2 style='display: inline-block;'>局部组件一：{{num}}</h2><button @click='num++'>点击计数</button></div>",
                            data : function(){
                                return {
                                    num : 1,
                                };
                            },
                        };
                    </script>
                    
            3.注册并使用：
                1.注册到Vue实例中：key组件名，value组件对象。   (EcmaScrip6语法中，key、value相同时可简写为key)
                 
                2.使用：组件名作为标签：
                
                    1.注册：
                    <script>
                        new Vue({
                            el: "#app",
                            components:{
                                //comp2:comp2     //将定义的对象注册为组件   key组件名 value组件对象
                                comp2,             //简写
                            },
                        });
                    </script>
                    
                    2.使用：
                    <body>
                        <div id="app">
                            <comp2></comp2>
                        </div>
                    </body>
        
        
    7.组件间的通信：
        1.通常一个单页应用由许多个组件组成，因此需要组件间进行通信。

        
        1.props（父向子传递）
            1.父组件使用子组件，定义 xx属性。
            2.子组件通过 props接收父组件 xx属性。
            3.通过 html子组件 v-bind:指令绑定父属性，进行传递。
                
            子组件：
                const comp2 = {
                    template : "<div>{{haha}}</div>",
                    props : ['haha'],       //props 接收父组件属性
                };
                
            父组件：
                new Vue({
                    el : "#app",
                    data : {
                        message : "你们好！",
                    }
                    components:{
                        comp2,             //简写
                    },
                });
                
            子组件标签：
                <comp2 :haha="message"></comp2>
        
 */

public class Vue详解 {

}
