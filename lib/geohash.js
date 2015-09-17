request = require("request");
moment  = require("moment");
crypto  = require("crypto");

module.exports = function(lat, lon, respond) {
	var yahooUrl = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22%5ENDX%22%2C%22INDU%22)&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=" 

	request(yahooUrl, function(error, response, body) {
		var dateToday  = moment().format('YYYY-MM-DD');
		var dowOpening = JSON.parse(body).query.results.quote[0].Open;

		var md5Hash = crypto.createHash('md5').update(dateToday + "-" + dowOpening).digest("hex");

		var latDecimal = parseInt(md5Hash.substring(0, 16), 16);
		var lonDecimal = parseInt(md5Hash.substring(16, 32), 16);

		respond({
			lat: lat.split('.')[0] + "." + latDecimal,
			lon: lon.split('.')[0] + "." + lonDecimal
		});
	})
}
