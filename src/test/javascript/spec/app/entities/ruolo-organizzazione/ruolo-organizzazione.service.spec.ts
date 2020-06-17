import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { RuoloOrganizzazioneService } from 'app/entities/ruolo-organizzazione/ruolo-organizzazione.service';
import { IRuoloOrganizzazione, RuoloOrganizzazione } from 'app/shared/model/ruolo-organizzazione.model';

describe('Service Tests', () => {
  describe('RuoloOrganizzazione Service', () => {
    let injector: TestBed;
    let service: RuoloOrganizzazioneService;
    let httpMock: HttpTestingController;
    let elemDefault: IRuoloOrganizzazione;
    let expectedResult: IRuoloOrganizzazione | IRuoloOrganizzazione[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(RuoloOrganizzazioneService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new RuoloOrganizzazione(0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a RuoloOrganizzazione', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new RuoloOrganizzazione()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a RuoloOrganizzazione', () => {
        const returnedFromService = Object.assign(
          {
            idRuoloOrganizzazione: 1,
            ruoloInOrg: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of RuoloOrganizzazione', () => {
        const returnedFromService = Object.assign(
          {
            idRuoloOrganizzazione: 1,
            ruoloInOrg: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a RuoloOrganizzazione', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
