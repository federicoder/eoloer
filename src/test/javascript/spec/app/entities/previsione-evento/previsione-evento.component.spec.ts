import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { PrevisioneEventoComponent } from 'app/entities/previsione-evento/previsione-evento.component';
import { PrevisioneEventoService } from 'app/entities/previsione-evento/previsione-evento.service';
import { PrevisioneEvento } from 'app/shared/model/previsione-evento.model';

describe('Component Tests', () => {
  describe('PrevisioneEvento Management Component', () => {
    let comp: PrevisioneEventoComponent;
    let fixture: ComponentFixture<PrevisioneEventoComponent>;
    let service: PrevisioneEventoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [PrevisioneEventoComponent],
      })
        .overrideTemplate(PrevisioneEventoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PrevisioneEventoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PrevisioneEventoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PrevisioneEvento(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.previsioneEventos && comp.previsioneEventos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
