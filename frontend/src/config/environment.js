/**
 * 环境配置
 *
 * @type {string}
 */

let baseUrl = 'http://1.116.123.171';

if (process.env.NODE_ENV === "development") {
    baseUrl = "http://1.116.123.171:8080";
} else if (process.env.NODE_ENV === "test") {
    baseUrl = "http://1.116.123.171:8080";
} else if (process.env.NODE_ENV === "production") {
    baseUrl = "http://1.116.123.171:8080";
}

let isProduction = false;

if (process.env.NODE_ENV === "production") {
    isProduction = true;
}

export {
    baseUrl,
    isProduction
}
