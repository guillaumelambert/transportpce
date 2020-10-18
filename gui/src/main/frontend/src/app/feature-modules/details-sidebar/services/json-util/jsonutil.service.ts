import {TopologyNode} from '../../models/topology-node.model';
import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class JSONUtilService {

  constructor() {
  }

  /**
   * Method for parsing JSONObject and retrun nested object representing json
   * @param objectKey key to be used as returned node identifier
   * @param jsonObject json object to parse
   */
  parseObject(objectKey: string, jsonObject: any) {
    const children: TopologyNode[] = [];
    // tslint:disable-next-line: forin
    for (const key in jsonObject) {
      let shortKey = key;
      if (key.indexOf(':') !== -1) {
        shortKey = key.substring(key.indexOf(':') + 1);
      }
      if (jsonObject[key] && Object.prototype.toString.call(jsonObject[key]) === '[object Array]') {
        let i = 0;
        jsonObject[key].forEach(object => {
          let objKey = shortKey;
          for (const k in object) {
            if (k.toString().includes('-id')) {
              objKey += ' [ ' + object[k] + ' ]';
              break;
            }
          }
          children.push(this.parseObject(objKey, object));
          ++i;
        });
      } else if (jsonObject[key] && Object.prototype.toString.call(jsonObject[key]) === '[object Object]') {
        children.push(this.parseObject(shortKey, jsonObject[key]));
      } else {
        children.push(new TopologyNode(shortKey + ': ' + jsonObject[key]));
      }
    }
    return new TopologyNode(objectKey, children);
  }

  /**
   * Method for searching for value in nested json and return its enclosing object
   * @param objectId value of node id to search on nested json
   * @param jsonObject json object to search in
   */
  searchForObjectInJson(objectId: string, jsonObject: any) {

    if (jsonObject instanceof Array) {
      for (const object of jsonObject) {
        const resFromArray = this.searchForObjectInJson(objectId, object);
        if (resFromArray) {
          return resFromArray;
        }
      }
    } else {
      // tslint:disable-next-line:forin
      for (const key in jsonObject) {
        if (jsonObject[key] && jsonObject[key].toString() === objectId && key.toString().includes('-id')) {
          return jsonObject;
        }
        if (jsonObject[key] && jsonObject[key].constructor !== 'test'.constructor) {
          const resFromObject = this.searchForObjectInJson(objectId, jsonObject[key]);
          if (resFromObject) {
            return resFromObject;
          }
        }
      }
    }
    return null;
  }
}
