export class KeyValuePair {
  constructor(private key: string, private value: string) {}

  getKey(): string {
    return this.key;
  }

  setKey(key: string) {
    this.key = key;
  }

  getValue(): string {
    return this.value;
  }

  setValue(value: string) {
    this.value = value;
  }
}
