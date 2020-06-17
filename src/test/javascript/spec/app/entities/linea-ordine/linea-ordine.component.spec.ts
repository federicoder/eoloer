import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { LineaOrdineComponent } from 'app/entities/linea-ordine/linea-ordine.component';
import { LineaOrdineService } from 'app/entities/linea-ordine/linea-ordine.service';
import { LineaOrdine } from 'app/shared/model/linea-ordine.model';

describe('Component Tests', () => {
  describe('LineaOrdine Management Component', () => {
    let comp: LineaOrdineComponent;
    let fixture: ComponentFixture<LineaOrdineComponent>;
    let service: LineaOrdineService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [LineaOrdineComponent],
      })
        .overrideTemplate(LineaOrdineComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LineaOrdineComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LineaOrdineService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new LineaOrdine(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.lineaOrdines && comp.lineaOrdines[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
