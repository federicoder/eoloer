import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { CondivisionePraticaDetailComponent } from 'app/entities/condivisione-pratica/condivisione-pratica-detail.component';
import { CondivisionePratica } from 'app/shared/model/condivisione-pratica.model';

describe('Component Tests', () => {
  describe('CondivisionePratica Management Detail Component', () => {
    let comp: CondivisionePraticaDetailComponent;
    let fixture: ComponentFixture<CondivisionePraticaDetailComponent>;
    const route = ({ data: of({ condivisionePratica: new CondivisionePratica(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [CondivisionePraticaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CondivisionePraticaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CondivisionePraticaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load condivisionePratica on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.condivisionePratica).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
