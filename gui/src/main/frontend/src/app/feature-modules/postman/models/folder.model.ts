import { Item } from './item.type';

export class Folder {
  private items: Item[] = [];
  constructor(private name: string) {}

  getName(): string {
    return this.name;
  }

  setName(name: string) {
    this.name = name;
  }

  addItem(item: Item) {
    this.items.push(item);
  }

  addItems(item: Item[]) {
    this.items.push(...item);
  }

  getItems(): Item[] {
    return this.items;
  }

}
