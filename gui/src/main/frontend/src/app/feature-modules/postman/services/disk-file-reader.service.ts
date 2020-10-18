import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DiskFileReaderService {
  readFileAsString(file: File, success: (data: any) => void, error: (error: any) => void): void {
    const fileReader: FileReader = new FileReader();
    fileReader.onload = (e) => success(fileReader.result);
    fileReader.onerror = (e) => error(e);
    fileReader.readAsText(file);
  }
}
