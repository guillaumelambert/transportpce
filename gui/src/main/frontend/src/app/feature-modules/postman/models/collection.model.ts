import { Item } from './item.type';

export class Collection {
  constructor(private name: string, private items: Item[] = []) {}

  getName(): string {
    return this.name;
  }

  addItem(item: Item) {
    this.items.push(item);
  }

  getItems(): Item[] {
    return this.items;
  }

  setItems(items: Item[]) {
    this.items = items;
  }
}
