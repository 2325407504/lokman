		<footer class="container"><div class="row"><div class="span12"><spring:message code="label.copyright"></spring:message></div></div></footer>
		<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
		<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/modernizr/2.6.2/modernizr.min.js"></script>
		<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jqueryui/1.10.0/jquery-ui.min.js"></script>
		<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.2.2/bootstrap.min.js"></script>
		<script type="text/javascript" src="/gelibolu/resources/dataTables/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="/gelibolu/resources/js/master.js"></script>
		<script type='text/javascript'>//<![CDATA[ 
		$(window).load(function(){
			if (!Modernizr.inputtypes['date']) {
				//$('input[type=date]').datepicker({
					//beforeShowDay: checkBadDates
				//});
			}
		});//]]>  
		function checkBadDates(date) {
			var day = date.getDate();
			if (day > 15) {
				return {0: false}
			} else {
				return {0: true}
			}
		}
		</script>

<!-- c:url value="/fms/data" var="fmsDataUrl" />
<script type="text/javascript">
$(document).ready(function() {
    var stringify_aoData = function (aoData) {
        var o = {};
        var modifiers = ['mDataProp_', 'sSearch_', 'iSortCol_', 'bSortable_', 'bRegex_', 'bSearchable_', 'sSortDir_'];
        jQuery.each(aoData, function(idx,obj) {
            if (obj.name) {
                for (var i=0; i < modifiers.length; i++) {
                    if (obj.name.substring(0, modifiers[i].length) == modifiers[i]) {
                        var index = parseInt(obj.name.substring(modifiers[i].length));
                        var key = 'a' + modifiers[i].substring(0, modifiers[i].length-1);
                        if (!o[key]) {
                            o[key] = [];
                        }
                        console.log('index=' + index);
                        o[key][index] = obj.value;
                        //console.log(key + ".push(" + obj.value + ")");
                        return;
                    }
                }
                //console.log(obj.name+"=" + obj.value);
                o[obj.name] = obj.value;
            }
            else {
                o[idx] = obj;
            }
        });
        return JSON.stringify(o);
    };

    $('#example').dataTable( {
        "bProcessing": true,
        "bServerSide": true,
        "sAjaxSource": "${fmsDataUrl}",
        "fnServerData": function ( sSource, aoData, fnCallback ) {
        	console.log(stringify_aoData(aoData));
            $.ajax( {
                dataType: 'json',
                contentType: "application/json;charset=UTF-8",
                type: 'POST',
                url: sSource,
                data: stringify_aoData(aoData),
                success: fnCallback,
                error : function (e) {
                    alert (e);
                }
            } );
        }
    } );
} );
</script-->

	</body>
</html>