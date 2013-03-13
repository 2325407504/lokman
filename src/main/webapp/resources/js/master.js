ARIPD = {
	all : function() {
		
		ARIPD.focusOnUsername();
		
	},

	focusOnUsername : function() {
		if ($("#j_username")) {
			$("#j_username").focus();
		}
	}
	
}

$(function() {
	ARIPD.all();
});