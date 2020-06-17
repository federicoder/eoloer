import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { TipoAllegatoComponent } from 'app/entities/tipo-allegato/tipo-allegato.component';
import { TipoAllegatoService } from 'app/entities/tipo-allegato/tipo-allegato.service';
import { TipoAllegato } from 'app/shared/model/tipo-allegato.model';

describe('Component Tests', () => {
  describe('TipoAllegato Management Component', () => {
    let comp: TipoAllegatoComponent;
    let fixture: ComponentFixture<TipoAllegatoComponent>;
    let service: TipoAllegatoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [TipoAllegatoComponent],
      })
        .overrideTemplate(TipoAllegatoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TipoAllegatoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TipoAllegatoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TipoAllegato(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tipoAllegatoes && comp.tipoAllegatoes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
